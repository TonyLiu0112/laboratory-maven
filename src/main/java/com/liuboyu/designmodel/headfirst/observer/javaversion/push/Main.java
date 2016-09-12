package com.liuboyu.designmodel.headfirst.observer.javaversion.push;

import com.liuboyu.designmodel.headfirst.observer.javaversion.push.display.CurrentConditionsDisplay4J;
import com.liuboyu.designmodel.headfirst.observer.javaversion.push.display.StatisticsDisplay4J;
import com.liuboyu.designmodel.headfirst.observer.javaversion.push.entity.Metadata;

/**
 * Created by Tony on 9/11/16.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherData4J weatherData4J = new WeatherData4J();

        new StatisticsDisplay4J(weatherData4J);
        new CurrentConditionsDisplay4J(weatherData4J);

        Metadata metadata = new Metadata();
        metadata.setTemperature(60);
        metadata.setPressure(55);
        metadata.setHumidity(30.4f);

        weatherData4J.setMeasurements(metadata);
        Thread.sleep(5000);

        metadata.setTemperature(63);
        metadata.setPressure(51);
        metadata.setHumidity(33.4f);
        weatherData4J.setMeasurements(metadata);
        Thread.sleep(5000);

        metadata.setTemperature(55);
        metadata.setPressure(11);
        metadata.setHumidity(90.1f);
        weatherData4J.setMeasurements(metadata);

    }
}
