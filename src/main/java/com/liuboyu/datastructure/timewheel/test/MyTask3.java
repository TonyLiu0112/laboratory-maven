package com.liuboyu.datastructure.timewheel.test;

import com.liuboyu.datastructure.timewheel.DelayTask;

import java.util.concurrent.TimeUnit;

public class MyTask3 extends DelayTask {

    @Override
    public long execTime() {
        return TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS) + 21;
    }

    @Override
    public void run() {
        System.out.println("我延时21秒执行");
    }
}
