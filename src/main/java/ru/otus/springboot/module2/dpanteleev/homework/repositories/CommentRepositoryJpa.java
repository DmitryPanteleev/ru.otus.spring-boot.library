package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import java.util.List;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByComment(String comment);

    void deleteCommentByComment(String comment);
}
