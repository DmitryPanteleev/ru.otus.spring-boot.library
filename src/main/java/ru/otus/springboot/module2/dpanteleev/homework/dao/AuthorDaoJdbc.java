package ru.otus.springboot.module2.dpanteleev.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Author> getAllAuthor() {
        return namedParameterJdbcOperations
                .query("Select * from AUTHOR", new AuthorMapper());
    }

    @Override
    public Author getAuthorById(long id) {
        return namedParameterJdbcOperations
                .queryForObject("Select * from AUTHOR where id = :id"
                        , Map.of("id", id), new AuthorMapper());
    }

    @Override
    public void addAuthor(String authorName) {
        namedParameterJdbcOperations.update("insert into AUTHOR (`full_name`) values(:fullName)",
                Map.of("fullName", authorName));
    }

    @Override
    public void deleteAuthorByFullName(String authorName) {
        namedParameterJdbcOperations.update("delete from Author where full_name like(:fullName)",
                Map.of("fullName", authorName));
    }

    @Override
    public void updateFullName(String oldFullName, String newFullName) {
        namedParameterJdbcOperations.update("update AUTHOR set full_name = :newFullName " +
                "where full_name = :oldFullName",
                Map.of("oldFullName", oldFullName, "newFullName", newFullName));
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String fullName = resultSet.getString("full_name");
            return new Author(id, fullName);
        }
    }
}
