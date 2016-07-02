package com.liuboyu.thread.wait;

/**
 * Created by Tony on 5/19/16.
 */
public class Demo {



    class Work implements  Runnable {

        private Object lock = new Object();

        public void demo() throws InterruptedException {
            synchronized (lock) {
                System.out.println("aaaaaa");
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("do work");
                    try {
                        lock.wait(5000); // 注意, wait方法是会释放对lock对象的同步, 其他以lock对象为同步块的处理都可以进入 例如demo方法
                        System.out.println("开始了");
                        Thread.currentThread().sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo d = new Demo();
        Work w = d.new Work();
        new Thread(w).start();
        Thread.sleep(1000);
        while (true) {
            w.demo();
            Thread.currentThread().sleep(1000);
        }

    }

}
