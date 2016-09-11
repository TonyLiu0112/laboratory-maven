package com.liuboyu.designmodel.headfirst.observer.display;

import com.liuboyu.designmodel.headfirst.observer.DisplayElement;
import com.liuboyu.designmodel.headfirst.observer.Observer;
import com.liuboyu.designmodel.headfirst.observer.Subject;

/**
 * 天气信息布告板
 * <p>
 * Created by Tony on 9/11/16.
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float pressure;
    private Subject subject; // 这里保留这对象的意义是留到之后可能出现的取消注册的动作

    public StatisticsDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "; Pressure: " + pressure + ".");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.pressure = pressure;
        display(); // 这里直接调用display不是最好的方式，最好的方式是通过MVC模式来实现
    }
}
