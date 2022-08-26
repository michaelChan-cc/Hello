package com.cc.design.behavioral.observer;

public class Publisher {
    public EventManager eventManager;//这里不公开，后面无法声明

    public Publisher() {
        this.eventManager = new EventManager("hi", "bye");
    }

    public void sayHi(String msg){
        eventManager.notify("hi", msg);
    }

    public void sayBye(String msg){
        eventManager.notify("bye", msg);
    }
}
