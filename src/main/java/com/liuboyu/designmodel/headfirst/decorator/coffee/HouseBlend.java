package com.liuboyu.designmodel.headfirst.decorator.coffee;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;

/**
 * 综合咖啡
 * <p>
 * Created by Tony on 9/16/16.
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 1.29;
    }
}
