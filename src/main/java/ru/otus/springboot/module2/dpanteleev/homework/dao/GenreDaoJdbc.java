package ru.otus.springboot.module2.dpanteleev.homework.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Genre> getAllGenre() {
        return namedParameterJdbcOperations
                .query("Select * from GENRE", new GenreMapper());
    }

    @Override
    public Genre getGenreById(long id) {
        return namedParameterJdbcOperations
                .queryForObject("Select * from GENRE where id = :id"
                        , Map.of("id", id), new GenreMapper());
    }

    @Override
    public void addGenre(String genreName) {
        namedParameterJdbcOperations.update("insert into GENRE (`genre`) values(:genreName)",
                Map.of("genreName", genreName));
    }

    @Override
    public void deleteGenreByGenreName(String genreName) {
        namedParameterJdbcOperations.update("delete from GENRE where genre like(:genreName)",
                Map.of("genreName", genreName));
    }

    @Override
    public void updateGenreName(String oldGenreName, String newGenreName) {
        namedParameterJdbcOperations.update("update GENRE set genre = :newGenreName " +
                        "where genre = :oldGenreName",
                Map.of("oldGenreName", oldGenreName, "newGenreName", newGenreName));
    }

    public static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String genre = resultSet.getString("genre");
            return new Genre(id, genre);
        }
    }
}
