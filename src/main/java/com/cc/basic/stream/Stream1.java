package com.cc.basic.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        System.out.println(list.size());

        List<Integer> list1 = new ArrayList<>();
        list.stream().map(x->list1.add(x));

        System.out.println(list);

//        list.stream().forEach(x -> list1.add(x));
//        System.out.println(list1.size());

//        List<Integer> list2 = new ArrayList<>();
//        list.parallelStream().forEach(x -> list2.add(x));
//        System.out.println(list2.size());

        List<Integer> list3 = list.parallelStream().collect(Collectors.toList());
        System.out.println(list3.size());
    }
}
