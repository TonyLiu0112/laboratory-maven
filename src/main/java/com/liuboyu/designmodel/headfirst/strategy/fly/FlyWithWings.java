package com.liuboyu.designmodel.headfirst.strategy.fly;

import com.liuboyu.designmodel.headfirst.strategy.FlyBehavlor;

/**
 * 火箭飞行行为
 * <p>
 * Created by Tony on 9/11/16.
 */
public class FlyWithWings implements FlyBehavlor {
    @Override
    public void fly() {
        System.out.println("fly with wings.");
    }
}
