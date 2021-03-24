package ru.otus.springboot.module2.dpanteleev.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);

//        AuthorDao authorDao = context.getBean(AuthorDaoJdbc.class);
//        System.out.println(authorDao.getAllAuthor());
//        System.out.println(authorDao.getAuthorById(1));
//        authorDao.addAuthor("Pushkin");
//        System.out.println(authorDao.getAuthorById(1));
//        authorDao.updateFullName("Pushkin", "Chekhov");
//        System.out.println(authorDao.getAuthorById(2));
//        authorDao.deleteAuthorByFullName("Chekhov");
//        System.out.println(authorDao.getAllAuthor());
//        GenreDao genreDao = context.getBean(GenreDaoJdbc.class);
////        genreDao.addGenre(new Genre(2, "adventure"));
//        genreDao.addGenre("fantasy");
//        genreDao.addGenre("action");
//        System.out.println(genreDao.getAllGenre());
//        BookDao bookDao = context.getBean(BookDaoJdbc.class);
//        bookDao.addBook("test_Book",
//                authorDao.getAuthorById(1),
//                List.of(genreDao.getGenreById(0), genreDao.getGenreById(1), genreDao.getGenreById(2))
//        );
//        System.out.println(genreDao.getAllGenre());
//        System.out.println(bookDao.getAllBook());
//        Console.main(args);
    }

}
