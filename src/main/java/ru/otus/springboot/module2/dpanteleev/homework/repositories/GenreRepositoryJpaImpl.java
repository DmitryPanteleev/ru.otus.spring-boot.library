package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            em.flush();
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g ", Genre.class);
        return query.getResultList();
    }

    @Override
    public List<Genre> findByName(String genre) {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g " +
                "where g.genre = :genre", Genre.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public void updateGenreNameById(long id, String genre) {
        Query query = em.createQuery("update Genre g " +
                "set g.genre = :genre " +
                "where g.id = :id");
        query.setParameter("genre", genre);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void delete(Genre genre) {
      em.remove(genre);
    }
}
