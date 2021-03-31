package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {

    Author save(Author author);
    Optional<Author> findById(long id);

    List<Author> findAll();
    List<Author> findByName(String fullName);

    void updateNameById(long id, String name);
    void deleteById(long id);
}
