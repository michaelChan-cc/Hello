package com.cc.basic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class Parallel {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        numbers.stream().forEach(out::println);

        //stream 并发
//        numbers.parallelStream().forEach(System.out::println);
//        numbers.stream().parallel().forEach(System.out::println);

        // 并发控制有序
//        numbers.parallelStream().forEachOrdered(out::println);

    }
}
