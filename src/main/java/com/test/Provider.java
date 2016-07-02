package com.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tony on 6/28/16.
 */
public class Provider implements Runnable {

    private final ArrayBlockingQueue<Object> queue;

    public Provider(ArrayBlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // TODO 这里不断往队列放东西的逻辑
        queue.add("12345");
        queue.add("123451");
        queue.add("123452");
        queue.add("123453");
        queue.add("123454");
    }
}
