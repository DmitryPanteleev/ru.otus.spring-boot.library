package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServicesImpl implements CommentServices {

    private final CommentRepositoryJpa repo;

    public CommentServicesImpl(CommentRepositoryJpa repo) {
        this.repo = repo;
    }

    @Transactional
    @Override
    public Comment create(String comment, long bookId) {
        return repo.save(new Comment(0, comment, bookId));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return repo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findByComment(String comment) {
        return repo.findByComment(comment);
    }

    @Transactional
    @Override
    public void updateCommentById(long id, String comment) {
        repo.updateCommentById(id, comment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }
}
