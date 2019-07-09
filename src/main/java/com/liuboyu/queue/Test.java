package com.liuboyu.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");


        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());


        LinkedBlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(100);

        linkedQueue.put("1");
        System.out.println(linkedQueue.take());
    }

}
