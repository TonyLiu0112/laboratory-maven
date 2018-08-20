package com.liuboyu;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random(1);
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
    }

}
