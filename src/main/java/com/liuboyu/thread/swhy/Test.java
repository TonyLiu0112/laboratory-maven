package com.liuboyu.thread.swhy;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {

        Integer collect = Stream.of(1, 2, 3, 4, 5).collect(new TestCollector());
        System.out.println(collect);

    }

    public static class TestCollector implements Collector<Integer, TestAggregate, Integer> {


        @Override
        public Supplier<TestAggregate> supplier() {
            return TestAggregate::new;
        }

        @Override
        public BiConsumer<TestAggregate, Integer> accumulator() {
            return (testAggregate, integer) -> {
                System.out.println(integer);
                if (integer < 4)
                    testAggregate.add(integer);
            };
        }

        @Override
        public BinaryOperator<TestAggregate> combiner() {
            return (testAggregate, testAggregate2) -> {
                TestAggregate r = new TestAggregate();
                r.add(testAggregate.getSum());
                r.add(testAggregate2.getSum());
                return r;
            };
        }

        @Override
        public Function<TestAggregate, Integer> finisher() {
            return TestAggregate::getSum;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.singleton(Characteristics.UNORDERED);
        }
    }

    public static class TestAggregate {

        private int sum = 0;

        public int getSum() {
            return sum;
        }

        public void add(int sum) {
            this.sum += sum;
        }


    }

}
