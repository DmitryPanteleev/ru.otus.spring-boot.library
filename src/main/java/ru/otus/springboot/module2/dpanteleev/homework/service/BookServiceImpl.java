package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
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
        genres.forEach(s -> genreList.add(genreService.create(s)));
        return bookRepositoryJpa.save(new Book(0, bookName, authorService.create(authorName), genreList, null));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
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
        return bookRepositoryJpa.findByName(fullName);
    }

    @Transactional
    @Override
    public boolean addComment(String bookName, String newComment) {
        val comment = commentRepo.save(new Comment(0, newComment));
        Optional<Book> bookOption = Optional.of(bookRepositoryJpa.findByName(bookName).get(0));
        if (bookOption.isPresent()){
            List<Comment> commentList = bookOption.get().getComments();
            commentList.add(comment);
           bookOption.get().setComments(commentList);
           return true;
        }else {
            return false;
        }
    }

    @Transactional
    @Override
    public void updateBookNameById(long id, String name) {
        bookRepositoryJpa.updateBookNameById(id, name);
    }

    @Transactional
    @Override
    public void delete(Book book) {
        bookRepositoryJpa.delete(book);
    }
}
