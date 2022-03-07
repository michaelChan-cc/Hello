package com.cc;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        List<String> abc = new ArrayList<>();
        abc.add("a1");
        abc.add("a2");
        System.out.println(StringUtils.join(abc, ","));
    }
}
