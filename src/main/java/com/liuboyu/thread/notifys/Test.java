package com.liuboyu.thread.notifys;

import static com.liuboyu.thread.notifys.Test.waiter;

/**
 * Created by Tony on 6/20/16.
 */
public class Test {

    static final Object waiter = new Object();

    public static void main(String[] args) throws InterruptedException {
        Work work = new Work();
        new Thread(work).start(); // subThread. wait forever!

        Thread.currentThread().sleep(3000);
        synchronized (waiter) {
            System.out.println("日");
            waiter.notify(); // notify subThread
        }
    }

}

class Work implements Runnable {

    @Override
    public void run() {
        synchronized (waiter) {
            System.out.println("work.");
            try {
                waiter.wait();
//                Thread.currentThread().sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("fuck.");
        }
    }
}