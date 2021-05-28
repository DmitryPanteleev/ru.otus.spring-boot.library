package ru.otus.springboot.module2.dpanteleev.homework.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

public interface GenreService {

    Mono<Genre> create(String genreName);
    Mono<Genre> findById(String id);

    Flux<Genre> findAll();
    Flux<Genre> findByName(String genreName);

    void updateGenreById(String id, String genre);
    void delete(Genre genre);
}
