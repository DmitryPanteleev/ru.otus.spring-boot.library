package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.dao.AuthorDao;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public boolean isExist(String fullName) {
        AtomicBoolean response = new AtomicBoolean(false);
        authorDao.getAllAuthor().forEach(author -> {
            if (author.getFullName().equals(fullName)) {
                response.set(true);
            }
        });
        return response.get();
    }

    @Override
    public Optional<Author> addAuthor(String fullName) {
        if (!isExist(fullName)) {
            authorDao.addAuthor(fullName);
        }
        return authorDao.getAllAuthor().stream().filter(author -> author.getFullName().equals(fullName)).findFirst();
    }
}
