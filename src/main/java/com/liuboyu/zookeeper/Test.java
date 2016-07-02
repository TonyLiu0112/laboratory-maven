package com.liuboyu.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class Test {
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws InterruptedException {

        long starttime = System.currentTimeMillis();
        ZkClient zkclient = new ZkClient("192.168.0.212:2181,192.168.0.213:2181,192.168.0.216:2181,192.168.0.217:2181,192.168.0.218:2181");
        System.out.println("连接zk耗时 >> " + (System.currentTimeMillis() - starttime));

        new Thread(() -> zkclient.subscribeDataChanges("/map/map-1/listener", new Listener())).start();

//        Thread.currentThread().sleep(Integer.MAX_VALUE);
		for (int i = 0; i < 1; i ++) {
			System.out.println("正在写入数据 >> " + i);
			zkclient.writeData("/map/map-1/listener", "fuck");
			Thread.currentThread().sleep(5000);
		}

    }
}

class Listener implements IZkDataListener {

    public void handleDataChange(String dataPath, Object data) throws Exception {
        System.out.println("监听到了数据变化 >> " + data);
    }

    public void handleDataDeleted(String dataPath) throws Exception {
        // TODO Auto-generated method stub

    }

}
