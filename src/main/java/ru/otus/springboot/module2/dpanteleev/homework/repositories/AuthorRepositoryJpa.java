package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.List;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {

    List<Author> findAuthorByFullName(String fullName);

    void deleteAuthorByFullName(String fullName);


}
