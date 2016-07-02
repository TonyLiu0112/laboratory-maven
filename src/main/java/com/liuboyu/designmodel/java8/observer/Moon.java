package com.liuboyu.designmodel.java8.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 月亮: 被观察对象
 * <p>
 * Created by Tony on 4/16/16.
 */
public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }

}
