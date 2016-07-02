package com.liuboyu.designmodel.java8.observer;

/**
 * 观察者-NASA
 * Created by Tony on 4/16/16.
 */
public class Nasa implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.equals("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
