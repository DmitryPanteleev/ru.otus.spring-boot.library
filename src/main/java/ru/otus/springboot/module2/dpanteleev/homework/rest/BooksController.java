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
import ru.otus.springboot.module2.dpanteleev.homework.service.AuthorService;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;

import java.util.List;

@Controller
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping({"/books", "/"})
    public String listBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "list";
    }

    @GetMapping("/books/book/edit")
    public String editBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(NotFoundBookException::new));
        model.addAttribute("authors", authorService.findAll());
        return "edit";
    }

    @PostMapping("/books/book/edit")
    public String saveBook(
            @RequestParam("id") String id,
            @RequestParam("bookName") String bookName,
            @RequestParam("author") String author,
            @RequestParam("genres") List genres,
            Model model) {
        val newBook = bookService.updateBook(id, bookName, author, genres);
        model.addAttribute(newBook);
        return "redirect:/books";
    }

    @PostMapping("/books/book/delete")
    public String deleteBook(
            @RequestParam("id") String id,
            @RequestParam("button") String button,
            Model model) {
        if (button.equals("delete") && bookService.findById(id).isPresent()){
            bookService.delete(bookService.findById(id).get());
        }
        return "redirect:/books";
    }
}
