package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {

    List<Genre> findGenreByGenre(String genre);

    void deleteGenreByGenre(String genre);
}
