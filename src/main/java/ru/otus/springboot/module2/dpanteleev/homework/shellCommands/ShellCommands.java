package ru.otus.springboot.module2.dpanteleev.homework.shellCommands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;
import ru.otus.springboot.module2.dpanteleev.homework.service.ConsolePrintService;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final ConsolePrintService cps;

    public ShellCommands(BookService bookService, ConsolePrintService cps) {
        this.bookService = bookService;
        this.cps = cps;
    }

    @ShellMethod(key = {"addBook"}, value = "Create book(bookName,bookAuthor,bookGenre/bookGenre/.../bookGenre)")
    public void addBook(String bookName, String bookAuthor, String bookGenre){
        List<String> genreList = Arrays.asList(bookGenre.split("/"));
        bookService.creatBook(bookName, bookAuthor, genreList);
        cps.printBookListToConsole(bookService.searchBook(bookName));
    }

    @ShellMethod(key = {"findAll"}, value = "Print all books")
    public void printAll(){
        cps.printBookListToConsole(bookService.searchAllBook());
    }
}
