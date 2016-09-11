package com.liuboyu.designmodel.headfirst.strategy.client;

import com.liuboyu.designmodel.headfirst.strategy.Duck;
import com.liuboyu.designmodel.headfirst.strategy.FlyBehavlor;
import com.liuboyu.designmodel.headfirst.strategy.QuackBehavlor;

/**
 * Created by Tony on 9/11/16.
 */
public class ModelDuck extends Duck {

    public ModelDuck(FlyBehavlor flyBehavlor, QuackBehavlor quackBehavlor) {
        setFlyBehavlor(flyBehavlor);
        setQuackBehavlor(quackBehavlor);
    }

    @Override
    public void display() {
        System.out.println("橡皮鸭子");
    }
}
