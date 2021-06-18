package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepositoryJpa repo;

    public CommentServicesImpl(CommentRepositoryJpa repo) {
        this.repo = repo;
    }

    @Transactional
    @Override
    public Mono<Comment> create(String comment, String bookId) {
        return repo.save(new Comment(comment, bookId));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Comment> findById(String id) {
        return repo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Comment> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Comment> findByComment(String comment) {
        return repo.findCommentByComment(comment);
    }

    @Transactional
    @Override
    public void updateCommentById(String id, String comment) {
        val entityComment = repo.findById(id);
        if (entityComment.blockOptional().isPresent()) {
            entityComment.blockOptional().get().setComment(comment);
            repo.insert(entityComment.block());
        }
    }

    @Transactional
    @Override
    public void deleteById(Comment comment) {
        repo.delete(comment);
    }

}
