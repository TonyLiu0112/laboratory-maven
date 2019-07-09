package com;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {
    public static void main(String[] args) {

//        Model model = new Model();
//        model.setShopName("1");
//        model.setShopCode("2");
//        model.setStatTime("3");
//        model.setHumanFlow(0);
//        model.setTrafficOutOfDoor(0);
//        model.setInStoreFlow(0);
//        model.setNewCustomers(0);
//        model.setOldCustomers(0);
//        model.setRemainDurationAvg(0);
//        model.setInStoreRate(new BigDecimal("0"));
//        model.setDepthInterviewsRate(new BigDecimal("0"));
//        model.setBounceRate(new BigDecimal("0"));
//        model.setEndTime("1");
//
//        System.out.println(JSON.toJSONString(model, true));
//
//        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
//        concurrentLinkedQueue.add(1);
//        concurrentLinkedQueue.add(2);
//        concurrentLinkedQueue.add(3);
//        concurrentLinkedQueue.add(4);
//        System.out.println(concurrentLinkedQueue);


        String s1 = new StringBuilder("go").append("od").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2);

    }
}
