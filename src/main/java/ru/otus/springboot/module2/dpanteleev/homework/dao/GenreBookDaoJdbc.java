package ru.otus.springboot.module2.dpanteleev.homework.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.List;
import java.util.Map;

@Repository
public class GenreBookDaoJdbc {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreBookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public List<Genre> genresBookList(long bookId){
        return namedParameterJdbcOperations
                .query("Select * from GENRE where id in (Select genre from BOOK_GENRE where book = :bookId)",  Map.of("bookId", bookId), new GenreDaoJdbc.GenreMapper());
    }

}
