package ru.otus.springboot.module2.dpanteleev.homework.service;

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
        return genreRepositoryJpa.save(new Genre(0, genreName));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> findById(long id) {
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
        return genreRepositoryJpa.findByName(genreName);
    }

    @Transactional
    @Override
    public void updateGenreById(long id, String genre) {
        genreRepositoryJpa.updateGenreNameById(id, genre);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreRepositoryJpa.deleteById(id);
    }
}
