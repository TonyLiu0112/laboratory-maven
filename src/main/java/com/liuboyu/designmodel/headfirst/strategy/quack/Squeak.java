package com.liuboyu.designmodel.headfirst.strategy.quack;

import com.liuboyu.designmodel.headfirst.strategy.QuackBehavlor;

/**
 * Created by Tony on 9/11/16.
 */
public class Squeak implements QuackBehavlor {
    @Override
    public void quack() {
        System.out.println("Zi~ zi~");
    }
}
