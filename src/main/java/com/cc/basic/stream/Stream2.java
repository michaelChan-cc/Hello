package com.cc.basic.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        String[] healthStatusZu = {"a","v","c"};
        System.out.println(Stream.of(healthStatusZu).collect(Collectors.joining(",")));
        System.out.println(Stream.of(healthStatusZu).limit(1).collect(Collectors.joining(",")));
        System.out.println(Stream.of(healthStatusZu).limit(9).collect(Collectors.joining(",")));


        System.out.println(Stream.of(healthStatusZu).map(i -> StringUtils.join("'", i, "'")).collect(Collectors.joining(",")));

        Stream<String> abc = Stream.of("123","563");
//        abc.forEach(System.out::println);

        String rst = abc.collect(Collectors.joining(","));

        System.out.println(rst);


//        List<Integer> list3 = list.parallelStream().collect(Collectors.toList());
//        System.out.println(list3.size());
    }
}
