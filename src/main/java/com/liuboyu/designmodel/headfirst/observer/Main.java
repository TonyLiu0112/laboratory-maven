package com.liuboyu.designmodel.headfirst.observer;

import com.liuboyu.designmodel.headfirst.observer.display.CurrentConditionsDisplay;
import com.liuboyu.designmodel.headfirst.observer.display.StatisticsDisplay;

/**
 * Created by Tony on 9/11/16.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();

        // 各种显示面板
        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(60, 55, 30.4f);
        Thread.sleep(5000);
        weatherData.setMeasurements(61.1f, 56, 30.5f);
        Thread.sleep(5000);
        weatherData.setMeasurements(59.91f, 57, 30.4f);
    }
}
