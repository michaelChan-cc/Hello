package com.cc.design.behavioral.observer;

import java.util.*;

/**
 * 基础发布者
 */
public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        Arrays.stream(operations).forEach(i -> this.listeners.put(i, new ArrayList<>()));
    }

    //某个观察者订阅特定类型的event
    public void subscribe(String eventType, EventListener eventListener){
        List<EventListener> users = listeners.get(eventType);
        users.add(eventListener);
    }

    public void unsubscribe(String eventType, EventListener eventListener){
        List<EventListener> users = listeners.get(eventType);
        users.remove(eventListener);
    }

    //发布者 调用某个event下所有的接口
    public void notify(String eventType, String msg){
        List<EventListener> users = listeners.get(eventType);
        users.forEach(i-> i.update(eventType, msg));
    }

}
