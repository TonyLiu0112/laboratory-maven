package com.liuboyu.designmodel.headfirst.factorymethod;

import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.*;

/**
 * 披萨的抽象
 * 1. 封装了披萨的基本原材料抽象
 * 2. 封装了披萨的加工做法、剪切、打包。
 * Created by Tony on 9/17/16.
 */
public abstract class Pizza {
    public String name;
    public Dough dough;
    public Sauce sauce;
    public Veggies veggies[];
    public Cheese cheese;
    public Pepperoni pepperoni;
    public Clams clams;

    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350.");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices.");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "披萨名称:" + name;
    }
}
