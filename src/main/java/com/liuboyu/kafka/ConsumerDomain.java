package com.liuboyu.kafka;

import com.liuboyu.kafka.consumer.Consumer;

public class ConsumerDomain {

    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer("my-replicated-topic", "g1");
        new Thread(consumer).start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
