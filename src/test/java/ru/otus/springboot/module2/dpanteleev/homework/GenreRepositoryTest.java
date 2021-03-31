package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.GenreRepositoryJpaImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Жанр")
@DataJpaTest
@Import(GenreRepositoryJpaImpl.class)
public class GenreRepositoryTest {

    private static final long FIRST_GENRE_ID = 1L;
    private static final String FIRST_GENRE_NAME = "adventure1";
    private static final int COUNT_GENRE = 6;

    @Autowired
    TestEntityManager em;

    @Autowired
    GenreRepositoryJpaImpl repo;

    @DisplayName("находится по ИД")
    @Test
    void shouldFindExpectedGenreById() {
        val optionalActualGenre = repo.findById(FIRST_GENRE_ID);
        val expectedGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(optionalActualGenre).isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedGenreFullName() {
        val actualAuthorList = repo.findByName(FIRST_GENRE_NAME);
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

    @DisplayName("меняет имя")
    @Test
    void shouldUpdateActualGenre(){
        String newName = "new genre";
        val actualAuthor = repo.save(new Genre(0, "oldGenre"));
        em.detach(actualAuthor);
        repo.updateGenreNameById(actualAuthor.getId(), newName);
        val expectedAuthor = em.find(Genre.class, actualAuthor.getId());
        assertThat(newName).isEqualTo(expectedAuthor.getGenre());
    }

    @DisplayName("Находит всех")
    @Test
    void shouldFindAllGenre(){
        val authorList = repo.findAll();
        assertThat(authorList.size()).isEqualTo(COUNT_GENRE);
    }

    @DisplayName("удаляет жанр")
    @Test
    void shouldDeleteAuthors(){
        val actualGenre = repo.save(new Genre(0, "deletedGenre"));
        em.detach(actualGenre);
        repo.deleteById(actualGenre.getId());
        val optionalActualGenre = repo.findById(actualGenre.getId());
        assertThat(optionalActualGenre.isPresent()).isFalse();
    }

}
