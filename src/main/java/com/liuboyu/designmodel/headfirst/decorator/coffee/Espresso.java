package com.liuboyu.designmodel.headfirst.decorator.coffee;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;

/**
 * 特浓咖啡
 * <p>
 * Created by Tony on 9/16/16.
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return .99;
    }
}
