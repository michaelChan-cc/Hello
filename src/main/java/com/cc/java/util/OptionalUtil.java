package com.cc.java.util;

import java.util.Optional;

/**
 * java.util.Optional 解决NPE， 代码更优雅
 */
public class OptionalUtil {

    public static void main(String[] args) {

        Optional<String> optional = Optional.of("");

        System.out.println(Optional.of("") + "  ispresent:" + Optional.of("").isPresent());
        System.out.println(Optional.empty()+ "  ispresent:" + Optional.empty().isPresent());

//        System.out.println(Optional.of(null)); // 直接NPE
        System.out.println(Optional.ofNullable(null)+ "  ispresent:" + Optional.ofNullable(null).isPresent());

        System.out.println(Optional.of("abc"));
        System.out.println(Optional.of("abc").orElse("123"));
        System.out.println(Optional.ofNullable(null).orElse("123"));

        System.out.println(Optional.ofNullable(null).orElse(getName()));
        System.out.println(Optional.ofNullable("dog").orElse(getName()));//getName() 永远执行

        System.out.println(Optional.ofNullable(null).orElseGet(()-> getName()));
        System.out.println(Optional.ofNullable("dog").orElseGet(()-> getName()));

        System.out.println(Optional.ofNullable(null).orElseThrow(()-> new RuntimeException("exp")));
//        System.out.println(Optional.ofNullable(null).orElseThrow(()-> getThrow()));
    }

    private static String getName(){
        System.out.println("here is method [getName]");
        return "juice";
    }

    private static String getThrow(){
        System.out.println("here is method [getThrow]");
        throw new RuntimeException("exp");
    }
}
