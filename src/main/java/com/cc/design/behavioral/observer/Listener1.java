package com.cc.design.behavioral.observer;

public class Listener1 implements EventListener{
    private String people;//监听的人

    public Listener1(String people) {
        this.people = people;
    }

    @Override
    public void update(String eventType, String msg) {
        System.out.println(people + "收到指令["+eventType+"]，说话：" + msg);
    }
}

class Listener2 implements EventListener{
    private String people;

    public Listener2(String people) {
        this.people = people;
    }

    @Override
    public void update(String eventType, String msg) {
        System.out.println(people + "收到指令["+eventType+"]，(别管msg)来跳舞!");
    }
}
