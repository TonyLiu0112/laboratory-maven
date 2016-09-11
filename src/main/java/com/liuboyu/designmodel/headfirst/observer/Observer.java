package com.liuboyu.designmodel.headfirst.observer;

/**
 * 观察者
 * <p>
 * Created by Tony on 9/11/16.
 */
public interface Observer {

    /**
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压强
     */
    void update(float temp, float humidity, float pressure);
}