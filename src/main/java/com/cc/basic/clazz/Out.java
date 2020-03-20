/**
 * @file: Out
 * @author: michael
 * @date: 2020/3/20 11:39
 * @copyright: 南京凯盛
 */
package com.cc.basic.clazz;

/**
 *  静态内部类
 *  成员内部类
 *  局部内部类
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/20 11:39
 */
public class Out {

    private static String a = "静态变量";// 静态变量

    private String b = "内部成员变量";//普通内部变量

    /**
     * 静态内部类 static 修饰
     *   1. 只能访问外部类 的静态变量
     *   2. 使用方法： Out.Inner t1 = new Out.Inner()
     *
     *   3. 使用举例：HashMap.Node 与外部类紧密 && 不依赖外部类的实例
     */
    public static class StaticInner{
        public void testPrint(){
            System.out.println("StaticInner--------------->");
            System.out.println(a);

//            System.out.println(b);
        }
    }

    /**
     *  成员内部类 static 修饰
     *   1. 只能访问外部类 的静态变量
     *   2. 只能给类内的方法使用
     *
     *   3. 使用举例：HashMap.Node 与外部类紧密 && 不依赖外部类的实例
     */
    public class Inner{
        public void testPrint(){
            System.out.println("Inner--------------->");
            System.out.println(a);
            System.out.println(b);
        }
    }

    public void testInnerClass(){
        //尝试使用内部类
        Inner t2 = new Inner();
        t2.testPrint();
    }

    /**
     *  局部内部类 = 局部类；  定义在方法中的类，且只能在该方法内使用
     *
     *
     */
    void method(){
        final String c = "这是method().c";

        class ClassInMethod{
            public void testPrint(){
                System.out.println("ClassInMethod--------------->");
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
            }
        }
        //在方法内使用该类
        new ClassInMethod().testPrint();
    }


    public static void main(String[] args) {
        Out.StaticInner t1 = new Out.StaticInner();
        t1.testPrint();

        Out out = new Out();
        out.method();
    }
}

class TestOut{
    public static void testInnerClass(){
        Out t2 = new Out();
        t2.testInnerClass();
    }

    public static void main(String[] args) {
        testInnerClass();
    }

}
