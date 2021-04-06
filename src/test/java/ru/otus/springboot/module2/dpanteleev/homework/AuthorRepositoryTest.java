package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.AuthorRepositoryJpa;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Автор")
@DataJpaTest
public class AuthorRepositoryTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final String FIRST_AUTHOR_NAME = "Ivan1";
    private static final int COUNT_AUTHORS = 6;

    @Autowired
    private AuthorRepositoryJpa repo;

    @Autowired
    TestEntityManager em;

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedAuthorFullName() {
        val actualAuthorList = repo.findAuthorByFullName(FIRST_AUTHOR_NAME);
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

    @DisplayName("удаляет автора")
    @Test
    void shouldDeleteAuthors(){
        val actualAuthor = repo.save(new Author(0, "deletedAuthors"));
        em.detach(actualAuthor);
        repo.deleteAuthorByFullName(actualAuthor.getFullName());
        val optionalActualAuthor = repo.findById(actualAuthor.getId());
        assertThat(optionalActualAuthor.isPresent()).isFalse();
    }

}
