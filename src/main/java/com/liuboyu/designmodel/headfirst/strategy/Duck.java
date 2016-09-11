package com.liuboyu.designmodel.headfirst.strategy;

/**
 * Created by Tony on 9/11/16.
 */
public abstract class Duck {

    private FlyBehavlor flyBehavlor;
    private QuackBehavlor quackBehavlor;

    public void swim() {
        System.out.println("do swim");
    }

    public abstract void display();

    public void doQuack() {
        quackBehavlor.quack();
    }

    public void doFly() {
        flyBehavlor.fly();
    }

    public void setFlyBehavlor(FlyBehavlor flyBehavlor) {
        this.flyBehavlor = flyBehavlor;
    }

    public void setQuackBehavlor(QuackBehavlor quackBehavlor) {
        this.quackBehavlor = quackBehavlor;
    }
}
