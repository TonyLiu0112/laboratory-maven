package com.liuboyu.kafka;

import com.liuboyu.kafka.consumer.Consumer;

public class ConsumerDomain {

    public static void main(String[] args) {
        Consumer consumer = new Consumer("wifi-raw-data-rs", "group_local");
        new Thread(consumer).start();
    }
}
