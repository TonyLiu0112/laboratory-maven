package com.liuboyu.designmodel.headfirst.decorator;

/**
 * 饮料
 * Created by Tony on 9/15/16.
 */
public abstract class Beverage {

    public String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
