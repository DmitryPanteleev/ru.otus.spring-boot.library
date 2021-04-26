//package ru.otus.springboot.module2.dpanteleev.homework;
//
//import lombok.val;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.shell.jline.InteractiveShellApplicationRunner;
//import org.springframework.shell.jline.ScriptShellApplicationRunner;
//import ru.otus.springboot.module2.dpanteleev.homework.service.AuthorServiceImpl;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DisplayName("тестирование сервиса: Автор")
////@DataJpaTest
//@SpringBootTest(properties = {
//        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
//        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
//})
//@Import(AuthorServiceImpl.class)
//public class AuthorServiceTest {
//
//    @Autowired
//    private AuthorServiceImpl service;
//
//    @Test
//    void repoUpdateNameTest() {
//        val newName = "newAuthorName";
//        val actualAuthor = service.findById(1);
//        service.updateNameById(actualAuthor.get().getId(), newName);
//        val expectedAuthor = service.findById(1);
//        assertThat(newName).isEqualTo(expectedAuthor.get().getFullName());
//    }
//}
