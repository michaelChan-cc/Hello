package com.cc.design.behavioral.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(/*classes = SpringTestApplication.class*/)
@ActiveProfiles
class StrategyUseServiceTest {

    @Autowired
    private StrategyUseService strategyUseService;

    @BeforeEach
    void setUp() {
        System.out.println("up");
    }

    @AfterEach
    void tearDown() {
        System.out.println("down");
    }

    /**
     *
     */
    @Test
     void resolveFile() {
        assertDoesNotThrow(() -> strategyUseService.resolveFile(FileTypeResolveEnum.File_A_RESOLVE, "aaaaa"));

        assertDoesNotThrow(() -> strategyUseService.resolveFile(FileTypeResolveEnum.File_B_RESOLVE, "bbbb"));
    }

    @Test
    void setApplicationContext() {
        assertEquals(1, 2);
        System.out.println("setApplicationContext");
    }
}