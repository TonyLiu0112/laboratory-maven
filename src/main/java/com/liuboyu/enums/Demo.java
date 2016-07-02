package com.liuboyu.enums;

/**
 * Created by Tony on 2/15/16.
 */
public class Demo {

    enum Color {
        RED, BLACK, GREEN, BLUE
    }

    public static void main(String[] args) {
        Color red = Color.RED;
        System.out.println(red.hashCode());
        System.out.println(Color.RED.hashCode());
        System.out.println("RED".equals(Color.RED));
    }

}
