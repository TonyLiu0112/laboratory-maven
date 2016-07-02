package com.liuboyu.thread.notifys;

/**
 * Created by Tony on 6/20/16.
 */
public class Test2 {

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Ha()).start();
        new Thread(new Ha()).start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    static class Ha implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("啊啊啊");
                try {
                    Thread.currentThread().sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

