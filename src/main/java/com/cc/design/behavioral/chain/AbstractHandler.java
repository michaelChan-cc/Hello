package com.cc.design.behavioral.chain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 有一个指向责任下一个对象的属性
 * 一个设置下一个对象的set方法
 * 给子类对象差异化实现的方法（如以下代码的doFilter方法）
 */
public abstract class AbstractHandler {

    /**
     * 责任链中下一个处理对象
     */
    private  AbstractHandler nextHandler;

    public void filter(HttpServletRequest request, HttpServletResponse response){
        doFilter(request, response);

        if (getNextHandler() != null){
            getNextHandler().filter(request, response);
        }
    }

    //给子类对象差异化实现的方法
    abstract void doFilter(HttpServletRequest request, HttpServletResponse response);



    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


}
