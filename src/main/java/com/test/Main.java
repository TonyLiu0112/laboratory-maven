package com.test;

/**
 * Created by Tony on 6/28/16.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        String c = "Liuboyu";
        String a = new String(c);
        String b = new String("Liuboyu");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }

}
