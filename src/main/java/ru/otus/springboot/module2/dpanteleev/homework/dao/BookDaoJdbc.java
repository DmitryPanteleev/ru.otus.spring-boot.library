package ru.otus.springboot.module2.dpanteleev.homework.dao;

import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreBookDaoJdbc genreBookDaoJdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao, GenreBookDaoJdbc genreBookDaoJdbc) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreBookDaoJdbc = genreBookDaoJdbc;
    }


    @Override
    public List<Book> getAllBook() {
        return namedParameterJdbcOperations
                .query("Select * from BOOK", new BookMapper(authorDao, genreBookDaoJdbc));
    }

    @Override
    public Book getBookById(long id) {
        return namedParameterJdbcOperations
                .queryForObject("Select * from BOOK where id = :id"
                        , Map.of("id", id), new BookMapper(authorDao, genreBookDaoJdbc));
    }

    @Override
    public void addBook(String bookName, Author author, List<Genre> genres) {
//        genres[]
        KeyHolder kh = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory psc = new PreparedStatementCreatorFactory("insert into BOOK (`book_name`, `author`) values(?, ?)", Types.VARCHAR, Types.BIGINT);
        namedParameterJdbcOperations.getJdbcOperations().update(psc.newPreparedStatementCreator(List.of(bookName, author.getId())), kh);
        for (Genre genre :
                genres) {
            namedParameterJdbcOperations.update("insert into BOOK_GENRE(book, genre) values(:book, :genre)",
                    Map.of("book", 2, "genre", genre.getId()));
        }

    }

    @Override
    public void deleteBookByFullName(String bookName) {
        namedParameterJdbcOperations.update("delete from BOOK where id = :id",
                Map.of("id", bookName));
    }

    @Override
    public void updateBookName(String oldBookName, String newBookName) {
        namedParameterJdbcOperations.update("update BOOK set book_name = :newBookName " +
                        "where genre = :oldBookName",
                Map.of("oldBookName", oldBookName, "newBookName", newBookName));
    }

    private static class BookMapper implements RowMapper<Book> {

        private final AuthorDao authorDao;
        private final GenreBookDaoJdbc genreBookDaoJdbc;

        private BookMapper(AuthorDao authorDao, GenreBookDaoJdbc genreBookDaoJdbc) {
            this.authorDao = authorDao;
            this.genreBookDaoJdbc = genreBookDaoJdbc;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String book = resultSet.getString("book_name");
            Author author = authorDao.getAuthorById(resultSet.getLong("author"));
            List<Genre> genreList = genreBookDaoJdbc.genresBookList(id);
            return new Book(id, book, author, genreList);
        }
    }
}
