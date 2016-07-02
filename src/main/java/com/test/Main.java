package com.test;

import jodd.util.StringUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tony on 6/28/16.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(100); // TODO 公共队列, 可以单独维护 这里简单写一下啊

        Thread t = new Thread(new Provider(queue));
        t.start();
        t.interrupt();
        Consumer consumer = new Consumer(queue);
        new Thread(consumer).start();

        while (true) {
            if (StringUtil.isNotBlank(consumer.getCode())) {
                System.out.println("处理完了。结果是:" + consumer.getCode());
                break;
            }
            Thread.currentThread().sleep(1000);
        }

    }

}
