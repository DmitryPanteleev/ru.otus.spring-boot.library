package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.AuthorRepositoryJpaImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Автор")
@DataJpaTest
@Import(AuthorRepositoryJpaImpl.class)
public class AuthorRepositoryTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final String FIRST_AUTHOR_NAME = "Ivan1";
    private static final int COUNT_AUTHORS = 6;

    @Autowired
    private AuthorRepositoryJpaImpl repo;

    @Autowired
    TestEntityManager em;

    @DisplayName("находится по ИД")
    @Test
    void shouldFindExpectedAuthorById() {
        val optionalActualAuthor = repo.findById(FIRST_AUTHOR_ID);
        val expectedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(optionalActualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedAuthorFullName() {
        val actualAuthorList = repo.findByName(FIRST_AUTHOR_NAME);
        val expectedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(actualAuthorList.get(0)).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Создаёт нового пользователя")
    @Test
    void shouldAddExpectedAuthor() {
        val actualAuthor = repo.save(new Author(0, "newTestAuthor"));
        val expectedAuthor = em.find(Author.class, actualAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Находит всех")
    @Test
    void shouldFindAllAuthors(){
        val authorList = repo.findAll();
        assertThat(authorList.size()).isEqualTo(COUNT_AUTHORS);
    }

    @DisplayName("удаляет автора")
    @Test
    void shouldDeleteAuthors(){
        val actualAuthor = repo.save(new Author(0, "deletedAuthors"));
        em.detach(actualAuthor);
        repo.delete(repo.findById(actualAuthor.getId()).get());
        val optionalActualAuthor = repo.findById(actualAuthor.getId());
        assertThat(optionalActualAuthor.isPresent()).isFalse();
    }

}
