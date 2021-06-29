package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String genreName);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    List<Genre> findByName(String genreName);

    void updateGenreById(long id, String genre);

    void deleteById(Genre genre);
}
