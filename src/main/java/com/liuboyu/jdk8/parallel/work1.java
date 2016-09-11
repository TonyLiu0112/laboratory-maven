package com.liuboyu.jdk8.parallel;

import org.apache.qpid.proton.codec.messaging.SourceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 并行操作
 * <p>
 * Created by Tony on 4/11/16.
 */
public class work1 {

    /**
     * 将下列例子改成并行求平方和操作.
     * code:
     *
     * public static int sequentialSumOfSquares(IntStream range) {
     *     return range.map(x -> x * x).sum();
     * }
     *
     */
    public static int parallelSum(IntStream range) {
        return range.parallel().map(x -> x * x).sum();
    }

    /**
     * 下列代码是把列表中的数字相乘, 然后再将所得结果乘以5. 顺序执行这段程序没问题, 但是并行执行时有一个缺陷,
     * 使用流并行化执行该段代码, 并修复缺陷.
     *
     * public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
     *     return linkedListOfNumbers.stream().reduce(5, (acc, x) -> x * acc);
     * }
     *
     * 答: 并行计算限制
     *  1. 初始值必须为组合函数的恒等值
     *  2. reduce操作必须符合结合律.
     *  3. 要避免持有锁.
     * 很显然这里违反了限制1.
     *
     */
    public static int parallelThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream().reduce(1, (acc, x) -> x * acc) * 5;
    }

    /**
     * 下列代码是计算列表中数字的平方和. 尝试改进代码性能, 单不得牺牲代码质量. 只需做一些简单的改动即可.
     *
     * public static int slowSumOfSquares(List<Integer> linkedListOfNumbers) {
     *     return linkedListOfNumbers.parallelStream().map(x -> x * x).reduce(0, (acc, x) -> acc + x);
     * }
     *
     * 答: 将参数类型修改为ArrayList
     *
     */
    public static int fastSumOfSquares(List<Integer> arrayListOfNumbers) {
        return arrayListOfNumbers.parallelStream().map(x -> x * x).reduce(0, (acc, x) -> acc + x);
    }

    public static void arraySums(int[] items) {
        Arrays.parallelPrefix(items, (left, right) -> left + right);
        IntStream.range(0, items.length).forEach(value -> {
            System.out.println(items[value]);
        });
    }

    public static void main(String[] args) {
//        // 测试1
//        System.out.println(parallelSum(IntStream.range(0, 10)));
//
//        // 测试2
//        System.out.println(parallelThrough(Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList())));
//
//        // 测试3
//        List<Integer> linked = new ArrayList<>();
//        for (int i = 0; i < 1000000; i++) {
//            linked.add(Integer.valueOf(i));
//        }
//        long start = System.currentTimeMillis();
//        System.out.println(fastSumOfSquares(linked));
//        System.out.println("use time:" + (System.currentTimeMillis() - start) + "ms");

        int[] arr = {1, 2, 3, 4, 5};
        arraySums(arr);

    }


}
