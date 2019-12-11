package com.liuboyu.zookeeper;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class KiisooTest {

    public static void main(String[] args) {
        ZkClient zkclient = new ZkClient("qs-zk-01:2181",  60000);
        List<String> children = zkclient.getChildren("/");
        System.out.println(children);
    }

}
