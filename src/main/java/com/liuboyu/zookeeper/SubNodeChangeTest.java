package com.liuboyu.zookeeper;

import org.I0Itec.zkclient.ZkClient;

import java.util.stream.IntStream;

/**
 * Created by Tony on 5/3/16.
 */
public class SubNodeChangeTest {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkclient = new ZkClient("127.0.0.1:2181");
        if (!zkclient.exists("/subchanges")) zkclient.createPersistent("/subchanges");

        // 3个临时节点
        new Thread(() -> {
            IntStream.range(0, 3).forEach(value -> zkclient.createEphemeralSequential("/subchanges/job-", Thread.currentThread().getName()));
            try {
                Thread.currentThread().sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 监听器
        new Thread(() -> {
            zkclient.subscribeChildChanges("/subchanges", (parentPath, currentChilds) -> {
                System.out.println("parentPath:" + parentPath);
                currentChilds.forEach(System.out::println);
            });
        }).start();

        // 删除临时节点中的任意一个
        Thread.currentThread().sleep(10000);
        zkclient.delete("/subchanges/job-0000000007");
        System.out.println("删除了...");
        Thread.currentThread().sleep(Long.MAX_VALUE);
    }

}
