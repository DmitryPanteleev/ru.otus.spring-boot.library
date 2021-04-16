package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {


    Book create(String bookName, String authorName, List<String> genres);
    Optional<Book> findById(String id);

    List<Book> findAll();
    List<Book> findByName(String bookName);
    boolean addComment(String bookName, String newComment);
    void updateBookNameById(String id, String bookName);
    void delete(Book book);
    List<Comment> getBookAllComments(String bookName);
}
