package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {

    List<Optional<Book>> findBookByBookName(String bookName);

    List<Book> findBookByAuthor_FullName(String authorFullName);

    void deleteBookByBookName(String bookName);

}
