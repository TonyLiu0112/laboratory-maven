package com.liuboyu.datastructure.timewheel.test;

import com.liuboyu.datastructure.timewheel.Motor;
import com.liuboyu.datastructure.timewheel.TimingWheel;

public class TimingWheelTest {

    public static void main(String[] args) throws InterruptedException {
        TimingWheel wt = new TimingWheel();
        new Motor(wt);
        wt.add(new MyTask1());
        wt.add(new MyTask2());
        wt.add(new MyTask3());
        Thread.sleep(Integer.MAX_VALUE);
    }

}
