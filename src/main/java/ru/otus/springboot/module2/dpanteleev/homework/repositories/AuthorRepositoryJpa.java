package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.List;

public interface AuthorRepositoryJpa extends MongoRepository<Author, String> {

    List<Author> findAuthorByFullName(String fullName);

    void deleteAuthorByFullName(String fullName);


}
