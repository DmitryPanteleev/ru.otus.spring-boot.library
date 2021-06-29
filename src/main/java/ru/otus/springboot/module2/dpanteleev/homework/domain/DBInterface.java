package ru.otus.springboot.module2.dpanteleev.homework.domain;

public interface DBInterface {

    long id = 0;

    default long getId() {
        return id;
    }

}
