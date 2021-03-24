package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;

@Service
public interface BookService {

    void creatBook(String bookName, String bookAuthor, List<String> bookGenre);
    void deleteBook(String bookName);
    List<Book> searchBook(String bookName);
    void updateInfoOnBook(String oldBookName, String newBookName);
    List<Book> searchAllBook();

}
