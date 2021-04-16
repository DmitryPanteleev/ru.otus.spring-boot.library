package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "comment")
public class Comment {

    @Id
    private String id;

    @NotNull
    private String comment;

    @NotNull
    private String bookId;

    public Comment(String comment, String bookId) {
        this.comment = comment;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return comment;
    }

}
