package ru.otus.springboot.module2.dpanteleev.homework.events;

import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Genre;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;

public class MongoCascadeDeleteGenreEventListener extends AbstractMongoEventListener<Genre> {

    private final BookRepositoryJpa bookRepositoryJpa;

    public MongoCascadeDeleteGenreEventListener(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        val bookList = bookRepositoryJpa.findBookByGenresId(event.getSource().get("_id").toString());
        bookList.flatMap(book -> {
                    val genres = book.getGenres();
                    genres.removeIf(it -> it.getId().equals(event.getSource().get("_id").toString()));
                    return bookRepositoryJpa.save(book);
                }
        );
    }
}
