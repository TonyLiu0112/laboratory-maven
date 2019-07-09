package com.liuboyu.oom;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 5, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

    public static void main(String[] args) throws InterruptedException {

        executorService.execute(() -> {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("我是老2");
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(Integer.MAX_VALUE);

        executorService.wait();

    }

    private void append() {
        String s1 = "nihao";
        String s2 = "nihao2";
        String s3 = "nihao3";
        String s4 = s1 + s2 + s3;
        System.out.println(s4);
        StringBuilder stringBuffer = new StringBuilder("aaa");
        stringBuffer.append("3333");
        System.out.println(stringBuffer.toString());
    }

}
