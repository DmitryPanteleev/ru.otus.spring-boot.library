package ru.otus.springboot.module2.dpanteleev.homework.rest;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping({"/books", "/"})
    public String listBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "list";

    }

    @RequestMapping("/books/book/edit")
    public Mono<Book> editBook(@RequestParam("id") String id) {
        return bookService.findById(id);
    }

    @RequestMapping("/books/book/save")
    public void saveBook(
            @RequestParam("id") String id,
            @RequestParam("bookName") String bookName,
            @RequestParam("author") String author,
            @RequestParam("genres") List genres) {
        val response = bookService.updateBook(id, bookName, author, genres);
        try {
            response.toFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/books/book/delete")
    public void deleteBook(
            @RequestParam("id") String id) {
        try {
            bookService.delete(bookService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

