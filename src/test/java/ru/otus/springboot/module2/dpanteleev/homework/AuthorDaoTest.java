package ru.otus.springboot.module2.dpanteleev.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springboot.module2.dpanteleev.homework.dao.AuthorDaoJdbc;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(properties = {
//        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
//        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
//})
@DisplayName("Дао для работы с авторами")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final int EXISTING_AUTHORS_ID = 2;
    private static final String EXISTING_AUTHORS_NAME = "Ivan";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;
//    @Autowired
//    private GenreBookDaoJdbc genreBookDaoJdbc;
//    @Autowired
//    private BookDaoJdbc bookDaoJdbc;

//    @Test
//    void contextLoads() {
//    }

    @DisplayName("возвращает правильное количество авторов в базе")
    @Test
    void getAuthorSize() {
        assertThat(EXPECTED_AUTHORS_COUNT).isEqualTo(authorDaoJdbc.getAllAuthor().size());
    }

    @DisplayName("возвращает автора по ид")
    @Test
    void getAuthor(){
        Author expectedAuthor = authorDaoJdbc.getAuthorById(0);
        assertThat(EXISTING_AUTHORS_NAME).isEqualTo(expectedAuthor.getFullName());
    }

    @DisplayName("добавляет и возвращает автора")
    @Test
    void addAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHORS_ID, "testAuthor1");
        authorDaoJdbc.addAuthor(expectedAuthor.getFullName());
        Author actualAuthor = authorDaoJdbc.getAllAuthor().stream().filter(it ->
                it.getFullName().equals(expectedAuthor.getFullName())).findFirst().get();
        assertThat(actualAuthor).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(expectedAuthor);
        assertThat(actualAuthor.getId()).isNotNull();
    }

    @DisplayName("delete author только если у него нет книг")
    @Test
    void deleteAuthor(){
        authorDaoJdbc.addAuthor("deletedAuthor");
        authorDaoJdbc.deleteAuthorByFullName("deletedAuthor");
        Optional<Author> actual = authorDaoJdbc.getAllAuthor().stream().filter(s ->
                s.getFullName().equals("deletedAuthor")).findFirst();
        assertThat(actual.isPresent()).isFalse();
    }

    @DisplayName("update fullName author")
    @Test
    void updateAuthor(){
        String newName = "Boris";
        authorDaoJdbc.updateFullName(EXISTING_AUTHORS_NAME, newName);
        Author expected = authorDaoJdbc.getAuthorById(0);
        assertThat(newName).isEqualTo(expected.getFullName());
    }

}
