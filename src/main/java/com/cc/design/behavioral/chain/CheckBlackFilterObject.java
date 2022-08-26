package com.cc.design.behavioral.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author michael
 */
@Component
@Order(2)
public class CheckBlackFilterObject extends AbstractHandler {
    @Override
    void doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("黑名单检查一下！");
    }
}
