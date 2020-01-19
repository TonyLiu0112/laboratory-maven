package com.liuboyu.datastructure.timewheel.test;

import com.liuboyu.datastructure.timewheel.Motor;
import com.liuboyu.datastructure.timewheel.TimingWheel;

public class TimingWheelTest {

    public static void main(String[] args) throws InterruptedException {
        // 初始化时间轮
        TimingWheel wt = new TimingWheel();
        // 初始化时间轮的发动机, 用于推动时间轮前进
        new Motor(wt);

        // 申明3个延时任务
        wt.register(new MyTask1());
        wt.register(new MyTask2());
        wt.register(new MyTask3());

        System.out.println("任务注册完毕, 等待执行...");
        // 等待任务执行
        Thread.sleep(Integer.MAX_VALUE);
    }

}
