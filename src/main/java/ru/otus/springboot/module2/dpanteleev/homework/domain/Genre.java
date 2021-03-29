package ru.otus.springboot.module2.dpanteleev.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {
    private final long id;
    private final String genre;
}
