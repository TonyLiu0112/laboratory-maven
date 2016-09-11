package com.liuboyu.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Tony on 8/1/16.
 */
public class Test {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList();

        for (int i = 0; i < 100000000; i ++){
            list.add(i);
        }
        System.out.println(list.size());

        long forStart = System.currentTimeMillis();
        int sums = 0;
        for (Integer integer : list) {
            sums += integer;
        }
        System.out.println("for: " + (System.currentTimeMillis() - forStart));

        long start = System.currentTimeMillis();
        int x = 0;
        list.parallelStream().mapToInt(value -> value).sum();
        System.out.println("stream: " + (System.currentTimeMillis() - start));
    }

}
