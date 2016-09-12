package com.liuboyu.designmodel.headfirst.observer.javaversion.push.display;

import com.liuboyu.designmodel.headfirst.observer.DisplayElement;
import com.liuboyu.designmodel.headfirst.observer.javaversion.push.WeatherData4J;
import com.liuboyu.designmodel.headfirst.observer.javaversion.push.entity.Metadata;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tony on 9/11/16.
 */
public class CurrentConditionsDisplay4J implements Observer, DisplayElement {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay4J(WeatherData4J weatherData4J) {
        weatherData4J.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData4J) {
            Metadata metadata = (Metadata) arg;
            this.temperature = metadata.getTemperature();
            this.humidity = metadata.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "; Humidity: " + humidity + ".");
    }
}
