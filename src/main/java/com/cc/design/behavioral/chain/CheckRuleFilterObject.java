package com.cc.design.behavioral.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 责任链上，每个对象的差异化处理，如本小节的业务场景，就有参数校验对象、安全校验对象、黑名单校验对象、规则拦截对象
 *
 * @author michael
 */
@Component
@Order(3)
public class CheckRuleFilterObject extends AbstractHandler {
    @Override
    void doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("规则检查一下！");
    }
}
