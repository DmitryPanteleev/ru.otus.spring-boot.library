package ru.otus.springboot.module2.dpanteleev.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.springboot.module2.dpanteleev.homework.dao.GenreDao;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public boolean isExist(String genreName) {
        AtomicBoolean responce = new AtomicBoolean(false);
        genreDao.getAllGenre().forEach(genre -> {
            if (genre.getGenre().equals(genreName)) {
                responce.set(true);
            }
        });
        return responce.get();
    }

    @Override
    public void addGenre(String genreName) {
        if (!isExist(genreName)){
            genreDao.addGenre(genreName);
        }
    }

    @Override
    public Optional<Genre> getGenre(String genreName) {
        if (!isExist(genreName)) addGenre(genreName);
        return genreDao.getAllGenre().stream().filter(genre -> genre.getGenre().equals(genreName)).findFirst();
    }
}
