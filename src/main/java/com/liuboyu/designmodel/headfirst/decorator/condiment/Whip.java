package com.liuboyu.designmodel.headfirst.decorator.condiment;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;
import com.liuboyu.designmodel.headfirst.decorator.CondimentDecorator;

/**
 * 奶泡
 * <p>
 * Created by Tony on 9/16/16.
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return .35 + beverage.cost();
    }
}
