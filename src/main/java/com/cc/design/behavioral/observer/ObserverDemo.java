package com.cc.design.behavioral.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Listener1 lucy = new Listener1("lucy");
        Listener1 michael = new Listener1("michael");
        Listener2 michael2 = new Listener2("michael");

        Publisher publisher = new Publisher();
        publisher.eventManager.subscribe("hi", lucy);
        publisher.eventManager.subscribe("bye", michael);
        publisher.eventManager.subscribe("bye", michael2);


        //
        publisher.sayBye("bye");
        publisher.sayHi(" hello ?!");
    }

}
