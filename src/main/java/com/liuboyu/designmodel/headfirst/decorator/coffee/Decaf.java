package com.liuboyu.designmodel.headfirst.decorator.coffee;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;

/**
 * 无咖啡因咖啡
 * <p>
 * Created by Tony on 9/16/16.
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return .61;
    }
}
