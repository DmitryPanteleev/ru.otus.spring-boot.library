//package ru.otus.springboot.module2.dpanteleev.homework.rest;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import ru.otus.springboot.module2.dpanteleev.homework.domain.Book;
//import ru.otus.springboot.module2.dpanteleev.homework.service.BookService;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
//import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
//@Component
//public class BooksFunctionalController {
//
//    private final BookService bookService;
//
//    public BooksFunctionalController(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> composedRouters(BookService bookService){
//        return route()
//                .GET("/func/books",
//                        accept( APPLICATION_JSON ),
//                        request ->  request.body(bookService.findAll().)
//                        )
//                .build();
//    }
//}
