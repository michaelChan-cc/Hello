package com.cc.design.behavioral.observer.eventbus;

public class EventBusDemo {
    public static void main(String[] args) {
        EventListener eventListener = new EventListener("lucy");
        EventListener eventListener1 = new EventListener("michael");

        EventBusCenter.register(eventListener);
        EventBusCenter.register(eventListener1);
        EventBusCenter.post(new NotifyEvent("{\"name\":\"cc\"}"));
    }
}
