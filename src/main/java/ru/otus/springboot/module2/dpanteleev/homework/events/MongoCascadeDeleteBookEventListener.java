package ru.otus.springboot.module2.dpanteleev.homework.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
import ru.otus.springboot.module2.dpanteleev.homework.repositories.CommentRepositoryJpa;

@Component
@RequiredArgsConstructor
public class MongoCascadeDeleteBookEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepositoryJpa comment;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        comment.deleteById(event.getSource().get("_id").toString());
    }
}
