package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Книга")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepositoryJpa repo;

    @Autowired
    private CommentRepositoryJpa comment;

    @Autowired
    TestEntityManager em;

    private static final long FIRST_BOOK_ID = 1L;
    private static final String FIRST_BOOK_NAME = "aZbuka1";
    private static final int COUNT_BOOK = 6;


    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedGenreFullName() {
        val actualBookList = repo.findBookByBookName(FIRST_BOOK_NAME);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBookList.get(0).get()).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Создаёт новую книгу")
    @Test
    void shouldAddExpectedGenre() {
        val actualBook = repo.save(new Book(0, "second book",
                new Author(0, "author"),
                List.of(new Genre(0, "genre"), new Genre(0, "genre2")), null));
        comment.save(new Comment(0, "comment"));
        comment.save(new Comment(0, "comment2"));
        val expectedBook = em.find(Book.class, actualBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("удаляет книгу")
    @Test
    void shouldDeleteAuthors() {
        val actualBook = repo.save(new Book(0, "second book",
                new Author(0, "author"),
                List.of(new Genre(0, "genre"), new Genre(0, "genre2")), null));
        comment.save(new Comment(0, "comment"));
        comment.save(new Comment(0, "comment2"));
        em.detach(actualBook);
        repo.delete(repo.findById(actualBook.getId()).get());
        val optionalActualBook = repo.findById(actualBook.getId());
        assertThat(optionalActualBook.isPresent()).isFalse();
    }

    @DisplayName("Количество запросов")
    @Test
    void countQuery() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        repo.findAll();
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
    }
}
