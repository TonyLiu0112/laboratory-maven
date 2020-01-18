package com.liuboyu.datastructure.timewheel.test;

import com.liuboyu.datastructure.timewheel.DelayTask;

import java.util.concurrent.TimeUnit;

public class MyTask1 extends DelayTask {

    @Override
    public long execTime() {
        return TimeUnit.MILLISECONDS.convert(System.currentTimeMillis(), TimeUnit.SECONDS) + 7;
    }

    @Override
    public void run() {
        System.out.println("我延时7秒执行");
    }
}
