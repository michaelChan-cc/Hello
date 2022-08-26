package com.cc.design.creational.singleton;

public class LanHanSingleton {
    private static LanHanSingleton instance;

    private LanHanSingleton(){

    }
//懒汉模式实例在需要用到的时候，才去创建，就比较懒。如果有则返回，没有则新建，需要加下 synchronized关键字，要不然可能存在线性安全问题
    public static LanHanSingleton getInstance(){
        if (instance == null) {
            instance = new LanHanSingleton();
        }
        return instance;
    }
}
