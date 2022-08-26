package com.cc.design.creational.singleton;

public class InnerClassSingleton {
    private static class InnerClassSingletonHolder{
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    private InnerClassSingleton(){}
//静态内部类的实现方式，效果有点类似双重校验锁。但这种方式只适用于静态域场景，双重校验锁方式可在实例域需要延迟初始化时使用。
    public static final InnerClassSingleton getInstance(){
        return InnerClassSingletonHolder.INSTANCE;
    }
}
