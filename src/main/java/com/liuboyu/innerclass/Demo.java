package com.liuboyu.innerclass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 19/03/2017.
 */
public class Demo {

    private void say() {
        System.out.println("Outer class say method.");
    }

    public void run() {
        InnerClass innerClass = new InnerClass();
        innerClass.subSay();
    }

    public class InnerClass {

        void subSay() {
            say();
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.run();

        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {

        }
    }
}
