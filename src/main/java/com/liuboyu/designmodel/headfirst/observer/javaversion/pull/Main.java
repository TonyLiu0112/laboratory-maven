package com.liuboyu.designmodel.headfirst.observer.javaversion.pull;

import com.liuboyu.designmodel.headfirst.observer.javaversion.pull.display.CurrentConditionsDisplay4J;
import com.liuboyu.designmodel.headfirst.observer.javaversion.pull.display.StatisticsDisplay4J;

/**
 * Created by Tony on 9/11/16.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherData4J weatherData4J = new WeatherData4J();

        new StatisticsDisplay4J(weatherData4J);
        new CurrentConditionsDisplay4J(weatherData4J);

        weatherData4J.setMeasurements(60, 55, 30.4f);
        Thread.sleep(5000);
        weatherData4J.setMeasurements(61.1f, 56, 30.5f);
        Thread.sleep(5000);
        weatherData4J.setMeasurements(59.91f, 57, 30.4f);

    }
}
