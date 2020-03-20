/**
 * @file: Reflection
 * @author: michael
 * @date: 2020/3/20 10:37
 * @copyright: 南京凯盛
 */
package com.cc.basic.reflec;

import com.cc.collections.collection.list.ArrayTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *
 *
 * @author michael
 * @version 1.0 Created on 2020/3/20 10:37
 */
public class Reflection {

    public static void main(String[] args) throws ClassNotFoundException {
        //1.属性变量方式
        Class clazz1 = Error.class;
        //2.get方法
        Exception ex = new Exception();
        Class clazz2 = ex.getClass();
        //3.静态方法，类加载路径。 【最安全，性能最优】
        Class clazz3 = Class.forName("java.lang.System");

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);

        Method[] methods =  clazz3.getDeclaredMethods();
        System.out.println("method : ");
        for (Method method : methods) {
            System.out.println(method.getName() + " : " + method.toString());
        }

        Field[] fields =  clazz3.getDeclaredFields();
        System.out.println("fields : ");
        for (Field field : fields) {
            System.out.println(field.getName() + " : " + field.toString());
        }

        Constructor[] constructors =  clazz3.getDeclaredConstructors();
        System.out.println("constructors : ");
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + " : " + constructor.toString());
        }

        /** 创建对象 **/
        try {
            //1.newInstance() 使用默认构造函数
            HashMap map = (HashMap) Class.forName("java.util.HashMap").newInstance();
            System.out.println(map.toString());

            try {
                // 2.获取构造函数， 再使用构造函数构建
                Constructor constructor = Class.forName("java.util.HashMap").getDeclaredConstructor(Integer.class, Float.class);
                try {
                    constructor.newInstance(16, 0.75f);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
