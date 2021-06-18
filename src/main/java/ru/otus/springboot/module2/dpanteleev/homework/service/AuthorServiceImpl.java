package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.AuthorRepositoryJpa;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa repositoryJpa;

    public AuthorServiceImpl(AuthorRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Transactional
    @Override
    public Mono<Author> create(String fullName) {
        return repositoryJpa.save(new Author(fullName));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Author> findById(String id) {
        return repositoryJpa.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Author> findAll() {
        return repositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Author> findByName(String fullName) {
        return repositoryJpa.findAuthorByFullName(fullName);
    }

    @Transactional
    @Override
    public void updateNameById(String id, String name) {
        repositoryJpa.findById(id).block().setFullName(name);
    }

    @Transactional
    @Override
    public void delete(Author author) {
        repositoryJpa.delete(author);
    }
}
