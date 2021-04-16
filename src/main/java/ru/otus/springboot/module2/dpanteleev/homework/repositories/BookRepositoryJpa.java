package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;

public interface BookRepositoryJpa extends MongoRepository<Book, String> {

    List<Book> findBookByBookName(String bookName);

    List<Book> findBookByAuthor_FullName(String authorFullName);

    List<Book> findBookByAuthorId(String authorId);

    void deleteBookByBookName(String bookName);

    List<Book> findBookByGenresId(String genreId);

}
