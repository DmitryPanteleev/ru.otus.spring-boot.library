package ru.otus.springboot.module2.dpanteleev.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableMongoRepositories
public class HomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);
    }

}
