package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.exceptions.NotFoundBookException;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Book create(String bookName, String authorName, List<String> genres) {
        List<Genre> genreList = new ArrayList();
        genres.forEach(s -> {

            if (genreService.findByName(s).isEmpty()) {
                genreList.add( genreService.create(s));
            } else {
                genreList.add( genreService.findByName(s).get(0));
            }

        });
        Author author;
        if (authorService.findByName(authorName).isEmpty()) {
            author = authorService.create(authorName);
        } else {
            author = authorService.findByName(authorName).get(0);
        }
        return bookRepositoryJpa.save(new Book(bookName, author, genreList));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(String id) {
        return bookRepositoryJpa.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByName(String fullName) {
        return bookRepositoryJpa.findBookByBookName(fullName);
    }

    @Transactional
    @Override
    public boolean addComment(String bookName, String newComment) {
        val bookOption = bookRepositoryJpa.findBookByBookName(bookName);
        if (!bookOption.isEmpty()) {
            commentRepo.save(new Comment(newComment, bookOption.get(0).getId()));
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public void updateBookNameById(String id, String bookName) {
        val book = bookRepositoryJpa.findById(id);
        if (book.isPresent()) {
            book.get().setBookName(bookName);
            bookRepositoryJpa.save(book.get());
        }
    }

    @Override
    public Book updateBook(String id, String bookName, String authorName, List<String> genres) {
        val genreList = new ArrayList<Genre>();
        val oldBook = bookRepositoryJpa.findById(id);
        // Проверим что редактируемая книга существует
        if (!oldBook.isPresent()) {
            throw new NotFoundBookException();
        } else {
            // Проверяем переданного автора
            if (authorName != null) {
                // ищем автора по имени если существует
                if (!authorService.findByName(authorName).isEmpty()) {
                    oldBook.get().setAuthor(authorService.findByName(authorName).get(0));
                } else {
                    oldBook.get().setAuthor(authorService.create(authorName));
                }
            }
        }
        // Если изменили имя обновим его
        if (!oldBook.get().getBookName().equals(bookName)) {
            oldBook.get().setBookName(bookName);
        }
        // Если поменяли жанр обновим их
        if (!genres.isEmpty()) {
            genres.forEach(g -> {
//                Если пререданный жанр не существует создадим его
                        if (genreService.findByName(g).isEmpty()) {
                            genreList.add(genreService.create(g));
                        } else {
                            genreList.add(genreService.findByName(g).get(0));
                        }
                    }
            );
            oldBook.get().setGenres(genreList);
        }
        return bookRepositoryJpa.save(oldBook.get());
    }

    @Transactional
    @Override
    public void delete(Book book) {
        bookRepositoryJpa.delete(book);
    }

    @Override
    public List<Comment> getBookAllComments(String bookName) {
        if (!findByName(bookName).isEmpty()) {
            return commentRepo.findCommentByBookId(findByName(bookName).get(0).getId());
        } else return List.of();
    }
}
