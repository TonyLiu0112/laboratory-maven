package com.liuboyu.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 有四个线程
 * 1. 仅输出1
 * 2. 仅输出2
 * 3. 仅输出3
 * 4. 仅输出4
 * 输出到四个文件:
 * A: 1 2 3 4 1 2 ...
 * B: 2 3 4 1 2 3 ...
 * C: 3 4 1 2 3 4 ...
 * D: 4 1 2 3 4 1 ...
 * <p>
 * Created by Tony on 18/05/2017.
 */
class Test {

    static Map<Integer, List<Integer>> fileMap = new HashMap<Integer, List<Integer>>() {{
        put(1, new ArrayList<>());
        put(2, new ArrayList<>());
        put(3, new ArrayList<>());
        put(4, new ArrayList<>());
    }};

    public static void main(String[] args) throws InterruptedException {
        int length = 14;
        Metadata metadata = new Metadata();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 1; i < 5; i++) {
            new Thread(new Printer(i, i, metadata, length, countDownLatch)).start();
        }
        countDownLatch.countDown();
        Thread.sleep(5000);
        fileMap.forEach((k, v) -> System.out.println("文件" + k + ": " + v.stream().map(integer -> integer + "")
                .collect(Collectors.joining(","))));
    }

}

class Printer implements Runnable {
    private final int v;
    private int offset;
    private final Metadata metadata;
    private final int length;
    private int layer;
    private final Object lock = new Object();
    private final CountDownLatch countDownLatch;

    Printer(int v, int offset, Metadata metadata, int length, CountDownLatch countDownLatch) {
        this.v = v;
        this.offset = offset;
        this.metadata = metadata;
        this.layer = metadata.getLayer();
        this.length = length;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < length; i++) {
            Test.fileMap.get(offset).add(v);
            metadata.add();
            while (true) {
                if (metadata.isFinish(layer)) {
                    layer++;
                    nextOffset();
                    break;
                } else {
                    try {
                        synchronized (lock) {
                            lock.wait(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void nextOffset() {
        offset = offset == 1 ? 4 : --offset;
    }
}

class Metadata {
    private AtomicInteger layer;
    private ConcurrentHashMap<Integer, Boolean> map = new ConcurrentHashMap<>();
    private AtomicInteger finished;

    Metadata() {
        map.put(1, false);
        layer = new AtomicInteger(1);
        finished = new AtomicInteger(0);
    }

    synchronized void add() {
        finished.incrementAndGet();
        if (finished.compareAndSet(4, 0)) {
            map.put(layer.get(), true);
            map.put(layer.get() + 1, false);
            layer.incrementAndGet();
        }
    }

    boolean isFinish(int layer) {
        return map.get(layer);
    }

    int getLayer() {
        return layer.get();
    }
}