package com.liuboyu.designmodel.headfirst.strategy.client;

import com.liuboyu.designmodel.headfirst.strategy.Duck;
import com.liuboyu.designmodel.headfirst.strategy.FlyBehavlor;
import com.liuboyu.designmodel.headfirst.strategy.QuackBehavlor;
import com.liuboyu.designmodel.headfirst.strategy.fly.FlyWithWings;
import com.liuboyu.designmodel.headfirst.strategy.quack.Squeak;

/**
 * Created by Tony on 9/11/16.
 */
public class Main {

    public static void main(String[] args) {
        FlyBehavlor flyBehavlor = new FlyWithWings();
        QuackBehavlor quackBehavlor = new Squeak();
        Duck duck = new ModelDuck(flyBehavlor, quackBehavlor);
        duck.display();
        duck.doFly();
        duck.doQuack();
    }
}
