package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

public interface AuthorRepositoryJpa extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findAuthorByFullName(String fullName);

    void deleteAuthorByFullName(String fullName);


}
