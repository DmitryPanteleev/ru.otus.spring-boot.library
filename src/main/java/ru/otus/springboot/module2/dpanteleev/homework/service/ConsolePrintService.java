package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;

@Service
public class ConsolePrintService {

    public void printBookListToConsole(List<Book> bookList) {
        bookList.forEach(book -> System.out.println("Книга: " + book.getBookName() + " Автор: " + book.getAuthor().getFullName()
                + " Жанры: " + book.getGenres().toString() + " " + " Комментарии: " + book.getComments().toString()));
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
