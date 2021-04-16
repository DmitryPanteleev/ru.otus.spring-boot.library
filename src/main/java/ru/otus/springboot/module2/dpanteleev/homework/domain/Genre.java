package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "genre")
public class Genre {

    @Id
    private String id;

    @NotNull
    private String genre;

    @Override
    public String toString() {
        return genre;
    }

    public Genre(String genre) {
        this.genre = genre;
    }
}
