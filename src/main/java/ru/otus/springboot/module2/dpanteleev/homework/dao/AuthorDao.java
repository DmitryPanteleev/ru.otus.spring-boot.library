package ru.otus.springboot.module2.dpanteleev.homework.dao;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.List;

public interface AuthorDao {
    /**
     * Получить всех авторов
     * @return
     */
    List<Author> getAllAuthor();

    /**
     * Найти автора по имени
     * @param id
     * @return
     */
    Author getAuthorById(long id);

    /**
     * создать автора
     * @param author
     */
    void addAuthor(String author);

    /**
     * Удалить автора по имени
     * @param author
     */
    void deleteAuthorByFullName(String author);

    /**
     * Обновить имя
     * @param oldFullName
     * @param newFullName
     */
    void updateFullName(String oldFullName, String newFullName);
}
