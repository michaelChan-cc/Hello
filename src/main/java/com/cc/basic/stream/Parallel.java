package com.cc.basic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parallel {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //stream 并发
//        numbers.parallelStream().forEach(System.out::println);
//        numbers.stream().parallel().forEach(System.out::println);

        numbers.parallelStream().forEachOrdered(System.out::println);

    }
}
