package com.liuboyu.designmodel.headfirst.decorator.test;

import com.liuboyu.designmodel.headfirst.decorator.Beverage;
import com.liuboyu.designmodel.headfirst.decorator.coffee.HouseBlend;
import com.liuboyu.designmodel.headfirst.decorator.condiment.Mocha;
import com.liuboyu.designmodel.headfirst.decorator.condiment.Whip;

/**
 * Created by Tony on 9/16/16.
 */
public class Main {
    public static void main(String[] args) {
        Beverage beverage = new HouseBlend();   // 创建一个基本咖啡（最小的内圈）
        beverage = new Mocha(beverage);         // 用Mocha装饰咖啡（第二个圈套住内圈）
        beverage = new Mocha(beverage);         // 用Mocha装饰咖啡（第二个圈套住上一个圈）
        beverage = new Whip(beverage);          // 用Whip装饰咖啡
        System.out.println(beverage.getDescription() + " " + beverage.cost());
    }
}
