package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {

    Comment save(Comment comment);
    Optional<Comment> findById(long id);

    List<Comment> findAll();
    List<Comment> findByComment(String comment);

    void updateCommentById(long id, String comment);
    void delete(Comment comment);
}
