package com.liuboyu.designmodel.java8.observer;

/**
 * 观察者模式: 用户观察登入到月球的组织的接口
 * <p>
 * Created by Tony on 4/16/16.
 */
public interface LandingObserver {

    void observeLanding(String name);

}