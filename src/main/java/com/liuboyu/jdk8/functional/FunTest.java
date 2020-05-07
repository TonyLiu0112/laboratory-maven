package com.liuboyu.jdk8.functional;

import jodd.datetime.TimeUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能性代码测试
 *
 * @author Tony
 */
public class FunTest {

    public static void main(String[] args) {
    }

    private static void test1() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
        List<Integer> list = integerStream.filter(range(1, 5)).collect(Collectors.toList());
        System.out.println(list);

        Stream<String> hello = Stream.of("Hello", "world");
        System.out.println(hello.flatMap(s -> Arrays.stream(s.split(""))).collect(Collectors.toList()));
        Optional<String> reduce = hello.reduce((s, s2) -> s += s2);

        System.out.println(reduce.orElse(""));
    }

    private static Predicate<Integer> range(int begin, int end) {
        Predicate<Integer> lessThan = n -> n > begin;
        Predicate<Integer> moreThan = n -> n < end;
        return lessThan.and(moreThan);
    }

}
