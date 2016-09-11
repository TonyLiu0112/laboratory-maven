package com.liuboyu.designmodel.headfirst.observer;

/**
 * 主题接口
 * <p>
 * Created by Tony on 9/11/16.
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
