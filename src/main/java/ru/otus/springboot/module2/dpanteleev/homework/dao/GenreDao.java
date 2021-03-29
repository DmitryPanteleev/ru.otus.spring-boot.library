package ru.otus.springboot.module2.dpanteleev.homework.dao;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;

public interface GenreDao {

    /**
     * Получить все жанры
     * @return
     */
    List<Genre> getAllGenre();

    /**
     * Найти жанр по имени
     * @param id
     * @return
     */
    Genre getGenreById(long id);

    /**
     * создать жанр
     * @param genreName
     */
    void addGenre(String genreName);

    /**
     * Удалить жанр по имени жанра
     * @param genreName
     */
    void deleteGenreByGenreName(String genreName);

    /**
     * Обновить жанр
     * @param oldGenreName
     * @param newGenreName
     */
    void updateGenreName(String oldGenreName, String newGenreName);
}
