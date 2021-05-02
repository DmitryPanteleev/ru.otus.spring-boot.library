package ru.otus.springboot.module2.dpanteleev.homework.shellCommands;

import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.service.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final ConsolePrintService cps;
    private final CommentServices commentServices;
    private final GenreService genreService;
    private final AuthorService authorService;

    public ShellCommands(BookService bookService, ConsolePrintService cps, CommentServices commentServices, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.cps = cps;
        this.commentServices = commentServices;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @ShellMethod(key = {"addBook"}, value = "Create book(bookName,bookAuthor,bookGenre/bookGenre/.../bookGenre)")
    public void addBook(String bookName, String bookAuthor, String bookGenre) {
        List<String> genreList = Arrays.asList(bookGenre.split("/"));
        Book book = bookService.create(bookName, bookAuthor, genreList);
        cps.printBookListToConsole(List.of(book));
    }

    @ShellMethod(key = {"findAll"}, value = "Print all books")
    public void printAll() {
        cps.printBookListToConsole(bookService.findAll());
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
                bookService.addComment(book.getBookName(), "fake_comment_" + i);
            }
        }
    }

    @ShellMethod(key = {"addComment"}, value = "add comment: addComment bookName \"comment\"")
    public void addComment(String bookName, @ShellOption(arity = 1) String comments) {
        if (!bookService.addComment(bookName, comments)) cps.printMessage("не найдена книга");
    }

    @ShellMethod(key = {"getComments"}, value = "get all comment: getComments  bookName ")
    public void getAllComments(String bookName) {
        cps.printMessage(bookService.getBookAllComments(bookName).toString());
    }

    @ShellMethod(key = {"removeGenre"}, value = "removeGenre genreName")
    public void removeGenre(String genreName) {
        val genres = genreService.findByName(genreName);
        genres.forEach(genreService::delete);
    }

    @ShellMethod(key = {"removeAuthor"}, value = "removeAuthor authorName")
    public void removeAuthor(String authorName) {
        val authors = authorService.findByName(authorName);
        System.out.println(authors.toString());
        authors.forEach(authorService::delete);
    }
}
