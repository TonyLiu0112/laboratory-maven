package com.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Tony on 6/28/16.
 */
public class Consumer implements Runnable {

    private String code = "";

    private final ArrayBlockingQueue<Object> queue;

    public Consumer(ArrayBlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Object obj = null;
        try {
            obj = queue.take();
            if (obj == null) return;
            int invokeCount = 0;
            while (invokeCount < 9) {
                if (methodA(obj)) {
                    code = "success";
                    return;
                }
                Thread.currentThread().sleep(30000);
                invokeCount++;
            }
            code = "failed";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean methodA(Object obj) {
        // TODO 你的业务逻辑 方法执行成功返回ture, 执行失败返回false
        return false;
    }

    public String getCode() {
        return code;
    }
}
