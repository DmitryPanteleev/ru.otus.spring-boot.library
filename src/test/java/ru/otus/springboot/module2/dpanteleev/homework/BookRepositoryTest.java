package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpaImpl;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpaImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Книга")
@DataJpaTest
@Import({BookRepositoryJpaImpl.class, CommentRepositoryJpaImpl.class})
public class BookRepositoryTest {

    @Autowired
    private BookRepositoryJpaImpl repo;

    @Autowired
    private CommentRepositoryJpaImpl comment;

    @Autowired
    TestEntityManager em;

    private static final long FIRST_BOOK_ID = 1L;
    private static final String FIRST_BOOK_NAME = "aZbuka1";
    private static final int COUNT_BOOK = 6;


    @DisplayName("находится по ИД")
    @Test
    void shouldFindExpectedGenreById() {
        val optionalActualBook = repo.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        System.out.println(optionalActualBook.get());
        assertThat(optionalActualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedGenreFullName() {
        val actualBookList = repo.findByName(FIRST_BOOK_NAME);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBookList.get(0)).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Создаёт новую книгу")
    @Test
    void shouldAddExpectedGenre() {

        val actualBook = repo.save(new Book(0, "second book",
                new Author(0, "author"),
                List.of(new Genre(0, "genre"), new Genre(0, "genre2")), null));
        comment.save(new Comment(0, "comment", actualBook.getId()));
        comment.save(new Comment(0, "comment2", actualBook.getId()));
//        em.detach(actualBook);
        System.out.println(actualBook);
        val expectedBook = em.find(Book.class, actualBook.getId());
        System.out.println(expectedBook);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("меняет имя")
    @Test
    void shouldUpdateActualGenre() {
        String newName = "new comment";
        val actualBook = repo.save(new Book(0, "second book",
                new Author(0, "author"),
                List.of(new Genre(0, "genre"), new Genre(0, "genre2")), null));
        comment.save(new Comment(0, "comment", actualBook.getId()));
        comment.save(new Comment(0, "comment2", actualBook.getId()));
        em.detach(actualBook);
        repo.updateBookNameById(actualBook.getId(), newName);
        val expectedBook = em.find(Book.class, actualBook.getId());
        assertThat(newName).isEqualTo(expectedBook.getBookName());
    }

    @DisplayName("Находит всех")
    @Test
    void shouldFindAllGenre() {
        val authorList = repo.findAll();
        assertThat(authorList.size()).isEqualTo(COUNT_BOOK);
    }

    @DisplayName("удаляет книгу")
    @Test
    void shouldDeleteAuthors() {
        val actualBook = repo.save(new Book(0, "second book",
                new Author(0, "author"),
                List.of(new Genre(0, "genre"), new Genre(0, "genre2")), null));
        comment.save(new Comment(0, "comment", actualBook.getId()));
        comment.save(new Comment(0, "comment2", actualBook.getId()));
        em.detach(actualBook);
        repo.deleteById(actualBook.getId());
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
