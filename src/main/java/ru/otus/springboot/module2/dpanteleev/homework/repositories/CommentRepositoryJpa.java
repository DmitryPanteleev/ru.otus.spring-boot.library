package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;

public interface CommentRepositoryJpa extends MongoRepository<Comment, String> {

    List<Comment> findCommentByComment(String comment);

    List<Comment> findCommentByBookId(String bookId);

    void deleteCommentByComment(String comment);
}
