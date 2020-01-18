package com.liuboyu.datastructure.timewheel;

public class Motor implements Runnable {

    private final TimingWheel tw;

    public Motor(TimingWheel tw) {
        this.tw = tw;
        new Thread(this).start();
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                tw.scroll();
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
