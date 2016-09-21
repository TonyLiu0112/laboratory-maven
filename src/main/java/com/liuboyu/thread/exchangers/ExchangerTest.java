package com.liuboyu.thread.exchangers;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程之间的数据交换,它提供一个同步点,在这个同步点,两个线程可以交换彼此的数据. 交换的两根线程必须持有相同的Exchanger对象, 如果是3线程，也遵循22互换原则
 * <p/>
 * <li>Exchanger可以用于遗传算法和管道设计.遗传算法里需要选出两个人作为交配的对象,这时候,会交换两个人的数据
 * 并使用交叉规则得出两个交配结果
 * </li>
 * <p/>
 * Created by Tony on 1/31/16.
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        // 线程1
        threadPool.execute(() -> {
            String a = "银行流水A";
            try {
                String result = exgr.exchange(a);
                System.out.println("我是线程1 => " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 线程2
        threadPool.execute(() -> {
            String b = "银行流水B";
            try {
                String result = exgr.exchange(b);
                System.out.println("我是线程2 => " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
    }

}
