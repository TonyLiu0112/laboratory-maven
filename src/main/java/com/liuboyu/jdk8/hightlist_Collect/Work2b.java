package com.liuboyu.jdk8.hightlist_Collect;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 假设一个元素为单词的流, 计算每个单词出现的次数. 假设输入如下, 则返回值为一个形如:[John -> 3, Paul -> 2, George -> 1] 的Map:
 * Stream<String> names = Stream.of("John", "Paul", "George", "John", "John", "Paul");
 * <p>
 * Created by Tony on 4/7/16.
 */
public class Work2b {

    static Map<String, Long> works(Stream<String> stream) {
        return stream.collect(Collectors.groupingBy(t -> t, Collectors.counting()));
    }

    public static void main(String[] args) {
        System.out.println(works(Stream.of("John", "Paul", "George", "John", "John", "Paul")));
    }

}
