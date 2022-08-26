package com.cc.design.creational.singleton;

public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() { }
//双重校验锁实现的单例模式，综合了懒汉式和饿汉式两者的优缺点。
// 以上代码例子中，在synchronized关键字内外都加了一层  if条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。

    public static DoubleCheckSingleton getInstance(){
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
