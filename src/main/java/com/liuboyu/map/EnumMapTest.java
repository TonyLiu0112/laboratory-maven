package com.liuboyu.map;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumMapTest {

    public enum State {
        PROCESSING, PAYMENT, FINISHED
    }

    public static void main(String[] args) {
        enumMapFun();
        hashMapFun();
    }

    public static void enumMapFun() {
        Map<State, String> enumMap = new EnumMap<>(EnumMapTest.State.class);
        enumMap.put(State.PROCESSING, "处理中");
        enumMap.put(State.PAYMENT, "支付中");
        enumMap.put(State.FINISHED, "完成");
        enumMap.forEach((state, s) -> System.out.println(state + ": " + s));
    }

    public static void hashMapFun() {
        Map<State, String> hashMap = new HashMap<>();
        hashMap.put(State.PROCESSING, "处理中");
        hashMap.put(State.PAYMENT, "支付中");
        hashMap.put(State.FINISHED, "完成");
        hashMap.forEach((state, s) -> System.out.println(state + ": " + s));
    }

}
