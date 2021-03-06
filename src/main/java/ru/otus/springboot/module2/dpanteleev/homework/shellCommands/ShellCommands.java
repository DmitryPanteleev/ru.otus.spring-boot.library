package ru.otus.springboot.module2.dpanteleev.homework.shellCommands;

import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;
import ru.otus.springboot.module2.dpanteleev.homework.service.ConsolePrintService;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final ConsolePrintService cps;


    public ShellCommands(BookService bookService, ConsolePrintService cps) {
        this.bookService = bookService;
        this.cps = cps;
    }

    @ShellMethod(key = {"findAll"}, value = "Print all books")
    public void printAll() {
        cps.printBookListToConsole(bookService.findAll().collectList().block());
    }

    @ShellMethod(key = {"generate"}, value = "generate many new book")
    public void generateFakeBook(int count) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                List<String> genreList = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    genreList.add("fake_genre" + j + i);
                }
                val book = bookService.create("fake_book_" + i, "fake_author_" + i, genreList);
                bookService.addComment(book.block().getBookName(), "fake_comment_" + i);
            }
        }
    }
}
