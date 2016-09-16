package com.liuboyu.designmodel.headfirst.decorator.condiment;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;
import com.liuboyu.designmodel.headfirst.decorator.CondimentDecorator;

/**
 * Created by Tony on 9/16/16.
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", mocha";
    }

    @Override
    public double cost() {
        return .7 + beverage.cost();
    }
}
