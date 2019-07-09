package com.liuboyu.thread;

import java.util.concurrent.Phaser;

public class PhaserTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {

        }
    }

    public class MyPhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
//            switch (phase)
//                case


            return super.onAdvance(phase, registeredParties);
        }
    }

    public class Task implements Runnable {

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            // 抵达
            String name = Thread.currentThread().getName();
            System.out.println(name + "到达.");
            phaser.arriveAndAwaitAdvance();

            // 第一题
            System.out.println(name + "第一题回答完毕");
            phaser.arriveAndAwaitAdvance();

            // 第二题
            System.out.println("第二题回答完毕");
            phaser.arriveAndAwaitAdvance();

            // 第三题
            System.out.println("第三题回答完毕");
            phaser.arriveAndAwaitAdvance();
        }
    }

}
