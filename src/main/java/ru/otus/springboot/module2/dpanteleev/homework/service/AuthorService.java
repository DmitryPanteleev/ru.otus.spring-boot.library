package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.Optional;

public interface AuthorService {

    boolean isExist(String fullName);
    Optional<Author> addAuthor(String fullName);

}
