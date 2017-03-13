package com.liuboyu.wrapper;

import java.util.HashSet;

/**
 * Test class.
 * <p>
 * Created by Tony on 13/03/2017.
 */
public class Test {
    public static void main(String[] args) {
        Counter<String> counter = new Counter<>(new HashSet<>());
        counter.add("a");
        System.out.println(counter.getCount());
        HashSet<String> source = new HashSet<String>(){{
            add("cc");
            add("dd");
            add("ee");
        }};
        counter.addAll(source);
        System.out.println(counter.getCount());
    }
}
