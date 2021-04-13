package ru.otus.springboot.module2.dpanteleev.homework.shellCommands;

import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;
import ru.otus.springboot.module2.dpanteleev.homework.service.CommentServices;
import ru.otus.springboot.module2.dpanteleev.homework.service.ConsolePrintService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final ConsolePrintService cps;
    private final CommentServices commentServices;

    public ShellCommands(BookService bookService, ConsolePrintService cps, CommentServices commentServices) {
        this.bookService = bookService;
        this.cps = cps;
        this.commentServices = commentServices;
    }

    @ShellMethod(key = {"addBook"}, value = "Create book(bookName,bookAuthor,bookGenre/bookGenre/.../bookGenre)")
    public void addBook(String bookName, String bookAuthor, String bookGenre){
        List<String> genreList = Arrays.asList(bookGenre.split("/"));
        Book book = bookService.create(bookName, bookAuthor, genreList);
        cps.printBookListToConsole(List.of(book));
    }

    @ShellMethod(key = {"findAll"}, value = "Print all books")
    public void printAll(){
        cps.printBookListToConsole(bookService.findAll());
    }

    @ShellMethod(key = {"generate"}, value = "generate many new book")
    public void generateFakeBook(int count){
//        int c = Integer.parseInt(count);
        for (int i = 0; i < count; i++) {
            List<String> genreList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                genreList.add("fake_genre" + j + i);
            }
            val book = bookService.create("fake_book_" + i, "fake_author_" + i, genreList);
            commentServices.create("fake_comment_"+ i);
        }
    }

    @ShellMethod(key = {"addComment"}, value = "add comment: addComment comment, bookName")
    public void addComment(String comment, String bookName){
        if (!bookService.addComment(bookName, comment)) cps.printMessage("не найдена книга");
    }

    @ShellMethod(key = {"getComments"}, value = "get all comment")
    public void getAllComments(long bookId){
        cps.printMessage(bookService.getAllComments(bookId).toString());
    }
}
