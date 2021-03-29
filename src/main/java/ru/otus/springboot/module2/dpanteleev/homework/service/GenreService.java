package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.Optional;

public interface GenreService {

    boolean isExist(String genreName);
    void addGenre(String genreName);
    Optional<Genre> getGenre(String genreName);
}
