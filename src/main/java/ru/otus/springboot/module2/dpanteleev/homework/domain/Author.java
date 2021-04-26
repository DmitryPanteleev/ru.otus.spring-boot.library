package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "author")
public class Author {

    @Id
    private String id;

    @NotNull
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }
}

