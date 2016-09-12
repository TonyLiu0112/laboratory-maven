package com.liuboyu.designmodel.headfirst.observer.javaversion.push;

import com.liuboyu.designmodel.headfirst.observer.javaversion.push.entity.Metadata;

import java.util.Observable;

/**
 * Created by Tony on 9/11/16.
 */
public class WeatherData4J extends Observable {

    public void setMeasurements(Metadata metadata) {
        setChanged();
        notifyObservers(metadata);
    }

}
