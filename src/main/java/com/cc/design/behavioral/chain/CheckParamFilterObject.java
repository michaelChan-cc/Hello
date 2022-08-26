package com.cc.design.behavioral.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author michael
 */
@Component
@Order(1)
public class CheckParamFilterObject extends AbstractHandler {
    @Override
    void doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("参数检查一下！");
    }
}
