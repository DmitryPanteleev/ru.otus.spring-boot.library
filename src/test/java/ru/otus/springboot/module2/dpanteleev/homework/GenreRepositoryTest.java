package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.GenreRepositoryJpa;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Жанр")
@DataJpaTest
public class GenreRepositoryTest {

    private static final long FIRST_GENRE_ID = 1L;
    private static final String FIRST_GENRE_NAME = "adventure1";
    private static final int COUNT_GENRE = 6;

    @Autowired
    TestEntityManager em;

    @Autowired
    GenreRepositoryJpa repo;

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedGenreFullName() {
        val actualAuthorList = repo.findGenreByGenre(FIRST_GENRE_NAME);
        val expectedAuthor = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(actualAuthorList.get(0)).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Создаёт нового пользователя")
    @Test
    void shouldAddExpectedGenre() {
        val actualAuthor = repo.save(new Genre(0, "newTestGenre"));
        val expectedAuthor = em.find(Genre.class, actualAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("удаляет жанр")
    @Test
    void shouldDeleteAuthors(){
        val genreName = "deletedGenre";
        val actualGenre = repo.save(new Genre(0, genreName));
        em.detach(actualGenre);
        repo.delete(repo.findGenreByGenre(genreName).get(0));
        val optionalActualGenre = repo.findById(actualGenre.getId());
        assertThat(optionalActualGenre.isPresent()).isFalse();
    }

}
