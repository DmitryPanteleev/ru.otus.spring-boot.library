package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            em.flush();
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("Select a from Author a ", Author.class);
        return query.getResultList();
    }

    @Override
    public List<Author> findByName(String fullName) {
        TypedQuery<Author> query = em.createQuery("Select a from Author a " +
                "where a.fullName = :fullname", Author.class);
        query.setParameter("fullname", fullName);
        return query.getResultList();
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }
}
