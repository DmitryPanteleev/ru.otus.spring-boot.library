package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            em.flush();
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("Select c from Comment c ", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByComment(String comment) {
        TypedQuery<Comment> query = em.createQuery("Select c from Comment c " +
                "where c.comment = :comment", Comment.class);
        query.setParameter("comment", comment);
        return query.getResultList();
    }

    @Override
    public void updateCommentById(long id, String comment) {
        Query query = em.createQuery("update Comment c set c.comment = :comment " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
