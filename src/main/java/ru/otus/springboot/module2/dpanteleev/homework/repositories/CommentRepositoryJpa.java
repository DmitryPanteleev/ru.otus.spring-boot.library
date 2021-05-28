package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

public interface CommentRepositoryJpa extends ReactiveMongoRepository<Comment, String> {

    Flux<Comment> findCommentByComment(String comment);

    Flux<Comment> findCommentByBookId(String bookId);

    void deleteCommentByComment(String comment);
}
