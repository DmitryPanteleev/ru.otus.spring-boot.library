package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

public interface GenreRepositoryJpa extends ReactiveMongoRepository<Genre, String> {

    Flux<Genre> findGenreByGenre(String genre);

    void deleteGenreByGenre(String genre);
}
