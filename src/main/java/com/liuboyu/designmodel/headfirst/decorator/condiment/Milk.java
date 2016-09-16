package com.liuboyu.designmodel.headfirst.decorator.condiment;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;
import com.liuboyu.designmodel.headfirst.decorator.CondimentDecorator;

/**
 * 牛奶饮料
 * <p>
 * Created by Tony on 9/16/16.
 */
public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
