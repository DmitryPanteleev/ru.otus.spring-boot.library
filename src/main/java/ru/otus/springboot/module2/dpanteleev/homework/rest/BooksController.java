package ru.otus.springboot.module2.dpanteleev.homework.rest;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.exceptions.NotFoundBookException;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;

import java.util.List;

@Controller
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(NotFoundBookException::new));
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(
            @RequestParam("id") String id,
            @RequestParam("bookName") String bookName,
            @RequestParam("author") String author,
            @RequestParam("genres") List genres,
            Model model) {
        val newBook = bookService.updateBook(id, bookName, author, genres);
        model.addAttribute(newBook);
        return "redirect:/";
    }
}
