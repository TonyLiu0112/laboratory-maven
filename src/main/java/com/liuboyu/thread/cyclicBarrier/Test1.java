package com.liuboyu.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 线程边间屏障,线程棚栏
 * <p/>
 * Created by Tony on 1/28/16.
 */
public class Test1 {

    //
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);


    /**
     * demo描述:
     * 4个人,约定一起到徐家汇集合,等所有人到了之后全部一起出发
     *
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
            final int flag = i;
            new Thread(() -> {
                try {
                    Thread.currentThread().sleep(flag * 2000L);
                    System.out.println("我[" + Thread.currentThread().getName() + "]到徐家汇了,等待其他人");
                    // 重置计数器
                    if (flag == 2) cyclicBarrier.reset();
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "] 出发.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

}
