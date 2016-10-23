package com.liuboyu.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by Tony on 22/10/2016.
 */
public class DockerClusterTest {

    public static void main(String[] args) {
        ZkClient zkclient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
//        zkclient.createPersistent("/liuboyu", "wori");
        System.out.println(zkclient.readData("/liuboyu").toString());
    }

}
