package ru.otus.springboot.module2.dpanteleev.homework.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.exceptions.NotFoundBookException;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;

import java.util.List;

@RestController
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public Flux<Book> getAllBooks() {
        return bookService.findAll();
    }

    @RequestMapping("/books/book/edit")
    public Mono<Book> editBook(@RequestParam("id") String id) {
        if (bookService.findById(id).blockOptional().isPresent()) {
            return bookService.findById(id);
        } else {
            throw new NotFoundBookException();
        }
    }

    @RequestMapping("/books/book/save")
    public void saveBook(
            @RequestParam("id") String id,
            @RequestParam("bookName") String bookName,
            @RequestParam("author") String author,
            @RequestParam("genres") List genres) {
        bookService.updateBook(id, bookName, author, genres);
    }

    @RequestMapping("/books/book/delete")
    public void deleteBook(
            @RequestParam("id") String id) {
        if (bookService.findById(id).blockOptional().isPresent()) {
            bookService.delete(bookService.findById(id).block());
        }
    }
}

