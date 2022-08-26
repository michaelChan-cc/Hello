package com.cc.design.creational.singleton;

public class EHanSingleton {
    private static EHanSingleton instance = new EHanSingleton();

    private EHanSingleton(){
    }
//饿汉模式，它比较饥饿、比较勤奋，实例在初始化的时候就已经建好了，不管你后面有没有用到，都先新建好实例再说。这个就没有线程安全的问题，但是呢，浪费内存空间呀。
    public static EHanSingleton getInstance() {
        return instance;
    }
}
