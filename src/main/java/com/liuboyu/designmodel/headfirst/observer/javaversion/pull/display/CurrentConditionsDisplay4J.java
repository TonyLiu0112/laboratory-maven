package com.liuboyu.designmodel.headfirst.observer.javaversion.pull.display;

import com.liuboyu.designmodel.headfirst.observer.DisplayElement;
import com.liuboyu.designmodel.headfirst.observer.javaversion.pull.WeatherData4J;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Tony on 9/11/16.
 */
public class CurrentConditionsDisplay4J implements Observer, DisplayElement {
    private WeatherData4J weatherData4J;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay4J(WeatherData4J weatherData4J) {
        this.weatherData4J = weatherData4J;
        weatherData4J.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData4J) {
            WeatherData4J weatherData4J = (WeatherData4J) o;
            this.temperature = weatherData4J.getTemperature();
            this.humidity = weatherData4J.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "; Humidity: " + humidity + ".");
    }
}
