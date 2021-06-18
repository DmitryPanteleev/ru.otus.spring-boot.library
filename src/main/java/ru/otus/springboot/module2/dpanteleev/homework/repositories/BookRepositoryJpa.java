package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

public interface BookRepositoryJpa extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findBookByBookName(String bookName);

    Flux<Book> findBookByAuthor_FullName(String authorFullName);

    Flux<Book> findBookByAuthorId(String authorId);

    void deleteBookByBookName(String bookName);

    Flux<Book> findBookByGenresId(String genreId);
}
