package ru.otus.springboot.module2.dpanteleev.homework.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Author;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.BookRepositoryJpa;

@Component
@RequiredArgsConstructor
public class MongoCascadeDeleteAuthorEventListener extends AbstractMongoEventListener<Author> {

    private final BookRepositoryJpa bookRepositoryJpa;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        val books = bookRepositoryJpa.findBookByAuthorId(event.getSource().get("_id").toString());
        books.buffer().blockFirst().forEach(bookRepositoryJpa::delete);
    }
}
