package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre save(Genre genre);
    Optional<Genre> findById(long id);

    List<Genre> findAll();
    List<Genre> findByName(String genre);

    void updateGenreNameById(long id, String genre);
    void delete(Genre genre);
}
