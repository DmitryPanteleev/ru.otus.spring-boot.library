package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String genreName);
    Optional<Genre> findById(String id);

    List<Genre> findAll();
    List<Genre> findByName(String genreName);

    void updateGenreById(String id, String genre);
    void delete(Genre genre);
}
