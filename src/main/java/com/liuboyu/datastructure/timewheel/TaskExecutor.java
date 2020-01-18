package com.liuboyu.datastructure.timewheel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskExecutor {

    private static LinkedBlockingQueue<DelayTask> taskQueue = new LinkedBlockingQueue<>();
    private static ExecutorService execPool = Executors.newFixedThreadPool(20);
    private static AtomicBoolean closed = new AtomicBoolean(false);

    static {
        start();
    }

    public static void publishTask(DelayTask delayTask) {
        taskQueue.add(delayTask);
    }

    private static void start() {
        new Thread(() -> {
            while (!closed.get()) {
                DelayTask task = null;
                try {
                    task = taskQueue.poll(2, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task == null)
                    continue;
                execPool.submit(task);
            }
        }).start();
    }
}
