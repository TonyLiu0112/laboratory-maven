package com.liuboyu.auto.unboxed;

/**
 * Created by Tony on 3/28/16.
 */
public class Unbelievable {
    static Integer i; // 不初始化就为null, 因为Integer是一个对象

    public static void main(String[] args) {
        if (i == 42) System.out.println("I am unbelievable.");
    }
}
