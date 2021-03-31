package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {


    Book create(String bookName, String authorName, List<String> genres);
    Optional<Book> findById(long id);

    List<Book> findAll();
    List<Book> findByName(String bookName);

    void updateBookNameById(long id, String bookName);
    void deleteById(long id);

}
