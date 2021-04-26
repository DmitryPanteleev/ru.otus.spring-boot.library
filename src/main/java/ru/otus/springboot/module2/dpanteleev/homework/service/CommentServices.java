package ru.otus.springboot.module2.dpanteleev.homework.service;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentServices {

    Comment create(String comment, String bookId);
    Optional<Comment> findById(String id);

    List<Comment> findAll();
    List<Comment> findByComment(String comment);

    void updateCommentById(String id, String comment);
    void deleteById(Comment comment);
}
