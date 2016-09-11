package com.liuboyu.jdk8.class1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 常用的流操作
 * <p>
 * Created by Tony on 3/29/16.
 */
public class StreamDemo {

    /**
     * collect(toList)
     */
    private void t1() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
    }

    /**
     * map
     * 例子,将集合里的所有字符串全部转换成大写字母
     */
    private void t2() {
        List<String> collected = Stream.of("a", "b", "c").map(s -> s.toUpperCase()).collect(Collectors.toList());
        collected.forEach(System.out::print);
    }

    public static void main(String[] args) {
        StreamDemo sd = new StreamDemo();
        sd.t2();
    }

}
