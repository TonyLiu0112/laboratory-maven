package com.liuboyu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {

    private final Process process = new Process();

    public void doService() throws InterruptedException {
        System.out.println("doService2");
        System.out.println(process.toString());
        Thread.sleep(1000L);
        process.stop();
    }

    private class Process {
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        public Process() {
            executorService.execute(MyRunnable::new);
        }

        private class MyRunnable implements Runnable {

            @Override
            public void run() {
                System.out.println(123);
            }
        }

        private void stop() {
            executorService.shutdown();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Test2 t = new Test2();
        t.doService();
        Thread.sleep(5000L);
        System.out.println("finished.");
    }

}
