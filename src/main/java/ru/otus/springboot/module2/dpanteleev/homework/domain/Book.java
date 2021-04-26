package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document(collection = "BOOK")
public class Book {

    @Id
    private String id;

    @NotNull
    private String bookName;

    @NotNull
    private Author author;

    @DBRef
    private List<Genre> genres;

    public Book(String bookName, Author author, List<Genre> genres) {
        this.bookName = bookName;
        this.author = author;
        this.genres = genres;
    }
}
