package com.liuboyu.designmodel.headfirst.decorator.coffee;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;

/**
 * 深度烘焙咖啡
 * <p>
 * Created by Tony on 9/16/16.
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return .83;
    }
}
