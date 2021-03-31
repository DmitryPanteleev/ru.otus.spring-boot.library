package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {

    Book save(Book book);
    Optional<Book> findById(long id);

    List<Book> findAll();
    List<Book> findByName(String bookName);

    void updateBookNameById(long id, String bookName);
    void deleteById(long id);
}
