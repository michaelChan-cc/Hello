package com.cc.design.behavioral.observer;

/**
 * 通用的观察者接口
 */
public interface EventListener {
    /**
     * 这个是关键动作，订阅后的结果体现就是 做一个数据更新
     * @param eventType
     * @param msg
     */
    void update(String eventType, String msg);
}
