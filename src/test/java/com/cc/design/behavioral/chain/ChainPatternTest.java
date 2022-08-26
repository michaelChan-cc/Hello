package com.cc.design.behavioral.chain;

import com.cc.design.behavioral.strategy.StrategyUseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import javax.xml.ws.Response;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles
class ChainPatternTest {

    @Autowired
    private ChainPattern pattern;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
        response = new MockHttpServletResponse();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void exec() {
        pattern.exec(request, response);
    }
}