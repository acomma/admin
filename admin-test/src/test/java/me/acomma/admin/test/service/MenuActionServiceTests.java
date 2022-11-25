package me.acomma.admin.test.service;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.core.service.MenuActionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class MenuActionServiceTests {
    @Autowired
    private MenuActionService menuActionService;

    @Test
    void test1() {
        List<String> codes = menuActionService.getMenuActionCodeByUserId(1000L);
        log.info("{}", codes);
    }
}
