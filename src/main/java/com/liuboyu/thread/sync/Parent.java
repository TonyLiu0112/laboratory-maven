package com.liuboyu.thread.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parent {

    public synchronized void p() {
        log.info("p is invoked.");
    }

}
