package me.acomma.admin.test.controller;

import me.acomma.admin.web.controller.MenuController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class SmokeTest {
    @Autowired
    private MenuController menuController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(menuController);
    }
}
