package com.liuboyu.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Tony on 6/23/16.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
        Thread.sleep(3000);
        countDownLatch.await();
        System.out.println("working");

    }

}
