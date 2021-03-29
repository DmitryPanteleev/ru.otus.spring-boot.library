package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.dao.BookDao;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }


    @Override
    public void creatBook(String bookName, String bookAuthor, List<String> bookGenre) {
        Optional<Author> author = authorService.addAuthor(bookAuthor);
        List<Genre> genreList = new ArrayList<>();
        bookGenre.forEach(s -> genreList.add(genreService.getGenre(s).get()));
        author.ifPresent(value -> bookDao.addBook(
                bookName,
                value,
                genreList
        ));
    }

    @Override
    public void deleteBook(String bookName) {
        bookDao.deleteBookByFullName(bookName);
    }

    @Override
    public List<Book> searchBook(String bookName) {
        return bookDao.getAllBook().stream().filter(book ->
                book.getBookName().equals(bookName)).collect(Collectors.toList());
    }

    @Override
    public void updateInfoOnBook(String oldBookName, String newBookName) {
        bookDao.updateBookName(oldBookName, newBookName);
    }

    @Override
    public List<Book> searchAllBook() {
        return bookDao.getAllBook();
    }
}
