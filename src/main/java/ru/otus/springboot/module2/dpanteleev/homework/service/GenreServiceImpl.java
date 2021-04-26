package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.GenreRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa genreRepositoryJpa;

    public GenreServiceImpl(GenreRepositoryJpa genreRepositoryJpa) {
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Transactional
    @Override
    public Genre create(String genreName) {
        return genreRepositoryJpa.insert(new Genre(genreName));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findById(String id) {
        return genreRepositoryJpa.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findByName(String genreName) {
        return genreRepositoryJpa.findGenreByGenre(genreName);
    }

    @Transactional
    @Override
    public void updateGenreById(String id, String genre) {
        val entityGenre = genreRepositoryJpa.findById(id);
        entityGenre.ifPresent(value -> value.setGenre(genre));
        genreRepositoryJpa.insert(entityGenre.get());

    }

    @Transactional
    @Override
    public void delete(Genre genre) {
        genreRepositoryJpa.delete(genre);
    }
}
