package ru.otus.springboot.module2.dpanteleev.homework.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

public interface AuthorService {

    Mono<Author> create(String fullName);
    Mono<Author> findById(String id);

    Flux<Author> findAll();
    Flux<Author> findByName(String fullName);

    void updateNameById(String id, String name);
    void delete(Author author);

}
