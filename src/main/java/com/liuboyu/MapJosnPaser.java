package com.liuboyu;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by Tony on 5/19/16.
 */
public class MapJosnPaser {

    private static ZkClient zkClient = new ZkClient("192.168.0.212:2181");

    public static void main(String[] args){
        String root = "/map";
        recursiveDel(root);
    }

    private static void recursiveDel(String path) {
        List<String> chiList = zkClient.getChildren(path);
        if (chiList != null) {
            for (String node : chiList) {
                recursiveDel(path + "/" + node);
            }
        }
        zkClient.delete(path);
    }

}
