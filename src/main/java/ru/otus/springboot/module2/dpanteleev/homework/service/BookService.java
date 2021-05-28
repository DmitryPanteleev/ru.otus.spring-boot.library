package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;

@Service
public interface BookService {


    Mono<Book> create(String bookName, String authorName, List<String> genres);
    Mono<Book> findById(String id);

    Flux<Book> findAll();
    Flux<Book> findByName(String bookName);
    boolean addComment(String bookName, String newComment);
    void updateBookNameById(String id, String bookName);
    Mono<Book> updateBook(String id, String bookName, String AuthorName, List<String> genres);
    void delete(Book book);
    Flux<Comment> getBookAllComments(String bookName);
}
