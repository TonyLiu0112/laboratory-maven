package com.liuboyu.thread.swhy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Service {

    public Service() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new Runner(), 2, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Fucker(), 2, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Lifer(), 2, 1, TimeUnit.SECONDS);
    }

    private class Printer implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Printer. " + this.hashCode());
        }
    }

    private class Driver implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Driver. " + this.hashCode());
        }
    }

    private class Runner implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Runner. " + this.hashCode());
        }
    }

    private class Fucker implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Fucker. " + this.hashCode());
        }
    }

    private class Lifer implements Runnable {

        @Override
        public void run() {
//            try {
//                System.out.println("aaa");
//                Thread.sleep(5000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + " Lifer. " + this.hashCode());
        }
    }
}
