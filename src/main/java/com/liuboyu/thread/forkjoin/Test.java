package com.liuboyu.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Test extends RecursiveTask<Long> {

    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;
    private final long start;
    private final long end;

    public Test(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        test();
        System.out.println("--------------------");
        testForkJoin();
    }

    private static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 0L; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static void testForkJoin() {
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new Test(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;

            Test task1 = new Test(start, mid);
            task1.fork();

            Test task2 = new Test(mid + 1, end);
            task2.fork();

            return task1.join() + task2.join();
        }
    }

}
