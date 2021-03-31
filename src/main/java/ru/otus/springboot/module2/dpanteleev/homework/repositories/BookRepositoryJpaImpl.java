package ru.otus.springboot.module2.dpanteleev.homework.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            em.flush();
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("Select b from Book b ", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String bookName) {
        TypedQuery<Book> query = em.createQuery("Select b from Book b " +
                "where b.bookName = :bookName", Book.class);
        query.setParameter("bookName", bookName);
        return query.getResultList();
    }

    @Override
    public void updateBookNameById(long id, String bookName) {
        Query query = em.createQuery("update Book b set b.bookName = :bookName " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("bookName", bookName);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book  b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
