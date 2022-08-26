package com.cc.design.creational.singleton;

public enum SingletonEnum {
    INSTANCE;
    //枚举实现的单例，代码简洁清晰。并且它还自动支持序列化机制，绝对防止多次实例化。
    public SingletonEnum getInstance(){
        return INSTANCE;
    }
}
