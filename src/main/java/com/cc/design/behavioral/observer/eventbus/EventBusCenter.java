package com.cc.design.behavioral.observer.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusCenter {
    private static EventBus eventBus = new EventBus();

    public EventBusCenter() {
    }

    public static EventBus getEventBus(){
        return eventBus;
    }

    //添加观察者
    public static void register(Object obj) {
        System.out.println("开始添加一个observer");
        eventBus.register(obj);
    }
    //移除观察者
    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    //把消息推给观察者
    public static void post(Object obj) {
        System.out.println("把消息[" + ((NotifyEvent)obj).getJsonMsg() + "]推给观察者");
        eventBus.post(obj);
    }
}
