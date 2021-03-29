package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String bookName;
    private final Author author;
    private final List<Genre> genre;
}
