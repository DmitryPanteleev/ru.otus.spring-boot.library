package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookRepositoryJpa repositoryJpa, AuthorService authorService, GenreService genreService, GenreService genreService1) {
        this.bookRepositoryJpa = repositoryJpa;
        this.authorService = authorService;
        this.genreService = genreService1;
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
    public void updateBookNameById(long id, String name) {
        bookRepositoryJpa.updateBookNameById(id, name);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepositoryJpa.deleteById(id);
    }
}
