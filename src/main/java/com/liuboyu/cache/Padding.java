package com.liuboyu.cache;

public class Padding {

    public static void main(String[] args) {
        Long[] arr = new Long[64 * 1024 * 1024];
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start);
        long start2 = System.nanoTime();
        for (int i = 0; i < arr.length; i += 8) {
            arr[i] *= 3;
        }
        System.out.println(System.nanoTime() - start2);
    }

}
