package com.liuboyu.kafka;

import kafka.producer.Partitioner;

public class MyPartitioner implements Partitioner {

    @Override
    public int partition(Object o, int i) {
        return 1;
    }
}
