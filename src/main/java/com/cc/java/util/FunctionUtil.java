package com.cc.java.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionUtil {

    static List<Integer> list = Arrays.asList(1,2,3,5);

    public static void main(String[] args) {
        function();
//        predicate();
//        supplier();
//        consumer();
    }

    static void function(){
        Function function = (Function<Integer, String>) o -> String.valueOf(o+1);
        System.out.println(function.apply(1));

        Function function1 = (Function<String, String>) s -> String.valueOf(Integer.valueOf(s)*2);
        System.out.println(function1.apply("2"));
        //链式执行； 函数1的return作为函数2的param
        System.out.println(function.andThen(function1).apply(2));
    }

    static void predicate(){
        Predicate<Integer> predicate = new Predicate<Integer>(){
            @Override
            public boolean test(Integer o) {
                return o>2;
            }
        };

        System.out.println(predicate.test(11));
        //stream应用
        list.stream().filter(predicate).forEach(System.out::println);
        //联合判定
        list.stream().filter(predicate.and(i -> i % 2 == 1)).forEach(System.out::println);

        Predicate.isEqual(predicate);
    }

    static void supplier(){
        Supplier<Double> supplier = () -> new Random().nextDouble();

        System.out.println(supplier.get());
    }

    static void consumer(){
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println(i);
                //do something...
            }
        };

        list.forEach(consumer);
    }
}
