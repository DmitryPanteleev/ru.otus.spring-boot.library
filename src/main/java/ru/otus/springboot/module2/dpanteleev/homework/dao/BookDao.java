package ru.otus.springboot.module2.dpanteleev.homework.dao;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;

public interface BookDao {
    /**
     * Получить все книги
     * @return
     */
    List<Book> getAllBook();

    /**
     * Найти книгу по названию
     * @param id
     * @return
     */
    Book getBookById(long id);

    /**
     * создать книгу
     * @param bookName
     * @param genres
     */
    void addBook(String bookName, Author author, List<Genre> genres);

    /**
     * Удалить автора по имени
     * @param bookName
     */
    void deleteBookByFullName(String bookName);

    /**
     * Обновить имя
     * @param oldBookName
     * @param newBookName
     */
    void updateBookName(String oldBookName, String newBookName);
}
