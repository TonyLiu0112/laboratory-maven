package com.liuboyu.designmodel.headfirst.observer.javaversion.pull;

import java.util.Observable;

/**
 * Created by Tony on 9/11/16.
 */
public class WeatherData4J extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
