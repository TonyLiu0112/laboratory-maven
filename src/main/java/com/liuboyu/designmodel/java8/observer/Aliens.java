package com.liuboyu.designmodel.java8.observer;

/**
 * 观察者-外星人, 用来观察到人类登入月球
 * <p>
 * Created by Tony on 4/16/16.
 */
public class Aliens implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.equals("Apollo")) {
            System.out.println("They're distracted, lets invade earth!");
        }
    }
}
