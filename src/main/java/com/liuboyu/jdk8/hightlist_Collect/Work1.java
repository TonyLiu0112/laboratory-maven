package com.liuboyu.jdk8.hightlist_Collect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 回顾第三章例子, 使用方法引用改写以下方法
 * 1. 转换大写的map方法;
 * 2. 使用reduce实现的count方法;
 * 3. 使用flatMap连接列表;
 * <p>
 * Created by Tony on 4/5/16.
 */
public class Work1 {

    /**
     * 转换大写的map方法;
     *
     * @param words
     */
    private static List<String> question1(List<String> words) {
        // old
        // return words.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        return words.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    /**
     * 使用reduce实现的count方法;
     *
     * @param numbers
     * @return
     */
    private static int count(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    /**
     * 使用flatMap连接列表
     *
     * @param lst1
     * @param lst2
     */
    private static List<Integer> joinList(List<Integer> lst1, List<Integer> lst2) {
        // old
        // return Stream.of(lst1, lst2).flatMap(integers -> integers.stream()).collect(Collectors.toList());
        return Stream.of(lst1, lst2).flatMap(Work1::getStream).collect(Collectors.toList());
    }

    public static Stream<Integer> getStream(List<Integer> list) {
        return list.stream();
    }

    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<>(Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList()));
//        System.out.println(count(numbers));


        List<Integer> l1 = Stream.of(1, 2).collect(Collectors.toList());
        List<Integer> l2 = Stream.of(3, 4).collect(Collectors.toList());
        System.out.println(joinList(l1, l2));

    }

}