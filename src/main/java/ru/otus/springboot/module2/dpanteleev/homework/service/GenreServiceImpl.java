package ru.otus.springboot.module2.dpanteleev.homework.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.GenreRepositoryJpa;

@Transactional
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa genreRepositoryJpa;

    public GenreServiceImpl(GenreRepositoryJpa genreRepositoryJpa) {
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    @Transactional
    @Override
    public Mono<Genre> create(String genreName) {
        return genreRepositoryJpa.insert(new Genre(genreName));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Genre> findById(String id) {
        return genreRepositoryJpa.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Genre> findAll() {
        return genreRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Genre> findByName(String genreName) {
        return genreRepositoryJpa.findGenreByGenre(genreName);
    }

    @Transactional
    @Override
    public void updateGenreById(String id, String genre) {
        val entityGenre = genreRepositoryJpa.findById(id);
        entityGenre.blockOptional().get().setGenre(genre);
        genreRepositoryJpa.insert(entityGenre.block());

    }

    @Transactional
    @Override
    public void delete(Genre genre) {
        genreRepositoryJpa.delete(genre);
    }
}
