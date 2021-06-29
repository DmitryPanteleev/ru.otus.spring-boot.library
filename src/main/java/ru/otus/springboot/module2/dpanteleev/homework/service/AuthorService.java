package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author create(String fullName);

    Optional<Author> findById(long id);

    List<Author> findAll();
    List<Author> findByName(String fullName);

    void updateNameById(long id, String name);

    void delete(Author author);

}
