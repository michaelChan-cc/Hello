package com.cc.design.behavioral.observer.eventbus;

public class NotifyEvent {
    private String jsonMsg;

    public NotifyEvent(String jsonMsg) {
        this.jsonMsg = jsonMsg;
    }

    public String getJsonMsg() {
        return jsonMsg;
    }

    public void setJsonMsg(String jsonMsg) {
        this.jsonMsg = jsonMsg;
    }

}
