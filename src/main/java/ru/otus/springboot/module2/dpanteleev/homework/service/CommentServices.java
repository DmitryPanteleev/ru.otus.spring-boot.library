package ru.otus.springboot.module2.dpanteleev.homework.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

public interface CommentServices {

    Mono<Comment> create(String comment, String bookId);
    Mono<Comment> findById(String id);

    Flux<Comment> findAll();
    Flux<Comment> findByComment(String comment);

    void updateCommentById(String id, String comment);
    void deleteById(Comment comment);
}
