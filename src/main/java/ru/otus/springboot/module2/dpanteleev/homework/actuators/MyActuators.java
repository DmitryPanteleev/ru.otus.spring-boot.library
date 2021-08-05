package ru.otus.springboot.module2.dpanteleev.homework.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;

@Component
public class MyActuators implements HealthIndicator {

    @Autowired
    BookService bookService;


    @Override
    public Health health() {
        if (bookService.count() > 0) {
            return Health.up().withDetail("library has elements", "good").build();
        } else {
            return Health.down().withDetail("library empty", "sorry").build();
        }
    }
}
