package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.exceptions.NotFoundBookException;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentRepositoryJpa commentRepo;

    public BookServiceImpl(BookRepositoryJpa repositoryJpa, AuthorService authorService, GenreService genreService, CommentRepositoryJpa comment) {
        this.bookRepositoryJpa = repositoryJpa;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentRepo = comment;
    }

    @Transactional
    @Override
    public Mono<Book> create(String bookName, String authorName, List<String> genres) {
        List<Genre> genreList = new ArrayList<Genre>();
        genres.forEach(s -> {

            if (genreService.findByName(s).hasElements().blockOptional().isPresent() && genreService.findByName(s).hasElements().blockOptional().get()) {
                genreList.add(genreService.create(s).block());
            } else {
                genreList.add(genreService.findByName(s).blockFirst());
            }

        });
        Author author;
        if (authorService.findByName(authorName).hasElements().blockOptional().isPresent() && authorService.findByName(authorName).hasElements().blockOptional().get()) {
            author = authorService.create(authorName).block();
        } else {
            author = authorService.findByName(authorName).blockFirst();
        }
        return bookRepositoryJpa.save(new Book(bookName, author, genreList));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Book> findById(String id) {
        return bookRepositoryJpa.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Book> findByName(String fullName) {
        return bookRepositoryJpa.findBookByBookName(fullName);
    }

    @Transactional
    @Override
    public boolean addComment(String bookName, String newComment) {
        val bookOption = bookRepositoryJpa.findBookByBookName(bookName);
        if (bookOption.singleOrEmpty().blockOptional().isPresent()) {
            commentRepo.save(new Comment(newComment, bookOption.blockFirst().getId()));
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public void updateBookNameById(String id, String bookName) {
        val book = bookRepositoryJpa.findById(id);
        if (book.blockOptional().isPresent()) {
            book.blockOptional().get().setBookName(bookName);
            bookRepositoryJpa.save(book.blockOptional().get());
        }
    }

    @Override
    public Mono<Book> updateBook(String id, String bookName, String authorName, List<String> genres) {
        val genreList = new ArrayList<Genre>();
        val oldBook = bookRepositoryJpa.findById(id);
        // Проверим что редактируемая книга существует
        if (oldBook.blockOptional().isEmpty()) {
            throw new NotFoundBookException();
        } else {
            // Проверяем переданного автора
            if (authorName != null) {
                // ищем автора по имени если существует
                if (!authorService.findByName(authorName).hasElements().blockOptional().get()) {
                    oldBook.blockOptional().get().setAuthor(authorService.findByName(authorName).blockFirst());
                } else {
                    oldBook.blockOptional().get().setAuthor(authorService.create(authorName).block());
                }
            }
        }
        // Если изменили имя обновим его
        if (!oldBook.blockOptional().get().getBookName().equals(bookName)) {
            oldBook.blockOptional().get().setBookName(bookName);
        }
        // Если поменяли жанр обновим их
        if (!genres.isEmpty()) {
            genres.forEach(g -> {
//                Если пререданный жанр не существует создадим его
                        if (genreService.findByName(g).hasElements().block()) {
                            genreList.add(genreService.create(g).blockOptional().get());
                        } else {
                            genreList.add(genreService.findByName(g).blockFirst());
                        }
                    }
            );
            oldBook.blockOptional().get().setGenres(genreList);
        }
        return bookRepositoryJpa.save(oldBook.blockOptional().get());
    }

    @Transactional
    @Override
    public void delete(Book book) {
        bookRepositoryJpa.delete(book);
    }

    @Override
    public Flux<Comment> getBookAllComments(String bookName) {
        if (findByName(bookName).hasElements().block()) {
            return commentRepo.findCommentByBookId(findByName(bookName).blockFirst().getId());
        } else return Flux.empty();
    }
}
