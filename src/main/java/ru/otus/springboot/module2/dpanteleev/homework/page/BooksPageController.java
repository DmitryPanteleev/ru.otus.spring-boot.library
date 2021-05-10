package ru.otus.springboot.module2.dpanteleev.homework.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksPageController {

    @GetMapping("/")
    public String listBooks(Model model) {
        return "list";
    }

}
