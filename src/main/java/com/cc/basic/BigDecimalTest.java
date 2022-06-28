package com.cc.basic;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(0.21456563214557);
        BigDecimal b = new BigDecimal(0.212);
        System.out.println(a.compareTo(b));

        System.out.println(new BigDecimal(0.21456563214557).compareTo(new BigDecimal(0.217)));
        System.out.println(new BigDecimal(0.21456563214557).compareTo(new BigDecimal(0.215)));

        System.out.println(a.add(new BigDecimal(-0.014)));

        System.out.println(new BigDecimal(0.21456563214557).compareTo(new BigDecimal(0.215)));

    }
}
