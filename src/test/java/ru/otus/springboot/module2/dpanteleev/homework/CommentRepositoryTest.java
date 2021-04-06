package ru.otus.springboot.module2.dpanteleev.homework;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Comment;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpaImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("тестирование репозитория: Комментарий")
@DataJpaTest
@Import(CommentRepositoryJpaImpl.class)
public class CommentRepositoryTest {

    private static final long FIRST_COMMENT_ID = 1L;
    private static final String FIRST_COMMENT_NAME = "first comment1";
    private static final int COUNT_COMMENT = 6;

    @Autowired
    private CommentRepositoryJpaImpl repo;

    @Autowired
    TestEntityManager em;

    @DisplayName("находится по ИД")
    @Test
    void shouldFindExpectedGenreById() {
        val optionalActualComment = repo.findById(FIRST_COMMENT_ID);
        val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(optionalActualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("Находится по имени")
    @Test
    void shouldFindExpectedGenreFullName() {
        val actualCommentList = repo.findByComment(FIRST_COMMENT_NAME);
        val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(actualCommentList.get(0)).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("Создаёт новый коммент")
    @Test
    void shouldAddExpectedGenre() {
        val actualComment = repo.save(new Comment(0, "second comment"));
        val expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("меняет коммент")
    @Test
    void shouldUpdateActualGenre(){
        String newName = "new comment";
        val actualComment = repo.save(new Comment(0, "oldComment"));
        em.detach(actualComment);
        repo.updateCommentById(actualComment.getId(), newName);
        val expectedComment = em.find(Comment.class, actualComment.getId());
        assertThat(newName).isEqualTo(expectedComment.getComment());
    }

    @DisplayName("Находит все комментарии")
    @Test
    void shouldFindAllGenre(){
        val commentList = repo.findAll();
        assertThat(commentList.size()).isEqualTo(COUNT_COMMENT);
    }

    @DisplayName("удаляет комментарий")
    @Test
    void shouldDeleteAuthors(){
        val comment = "deletedComment";
        val actualComment = repo.save(new Comment(0, comment));
        em.detach(actualComment);
        repo.delete(repo.findByComment(comment).get(0));
        val optionalActualComment = repo.findById(actualComment.getId());
        assertThat(optionalActualComment.isPresent()).isFalse();
    }


}
