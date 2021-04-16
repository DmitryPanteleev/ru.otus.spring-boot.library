package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.List;

@Service
public class ConsolePrintService {

    private final CommentRepositoryJpa commentRepositoryJpa;

    public ConsolePrintService(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    public void printBookListToConsole(List<Book> bookList) {
        bookList.forEach(book -> {
            String genres = "";
            String comments = "";
            if (book.getGenres() != null) {
                genres = book.getGenres().toString();
            }
            comments = commentRepositoryJpa.findCommentByBookId(book.getId()).toString();
            System.out.println("Книга: " + book.getBookName() + " Автор: " + book.getAuthor().getFullName()
                    + " Жанры: " + genres + " " + " Комментарии: " + comments);
        });
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
