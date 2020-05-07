package com.liuboyu.thread.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockService extends Parent {

    private final Logger logger = LoggerFactory.getLogger(MockService.class);

    public synchronized void aaa() {
        try {
            Thread.sleep(100000);
            p();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bbb() {
        p();
        logger.info("bbb");
    }

}
