package com.cc.design.behavioral.observer.eventbus;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    private String listenerName;

    public EventListener(String listenerName) {
        this.listenerName = listenerName;
    }

    @Subscribe //加了订阅，这里标记这个方法是事件处理方法
    public void handle(NotifyEvent notifyEvent){
        System.out.println(listenerName + "收到一条消息：" + notifyEvent.getJsonMsg());
    }

}
