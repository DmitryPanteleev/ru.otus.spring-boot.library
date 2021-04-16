package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;

public interface GenreRepositoryJpa extends MongoRepository<Genre, String> {

    List<Genre> findGenreByGenre(String genre);

    void deleteGenreByGenre(String genre);
}
