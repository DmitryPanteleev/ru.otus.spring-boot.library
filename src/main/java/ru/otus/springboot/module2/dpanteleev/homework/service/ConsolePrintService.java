package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;

import java.util.List;

@Service
public class ConsolePrintService {

    public void printBookListToConsole(List<Book> bookList){
        bookList.forEach(book -> System.out.println(book));
    }
}
