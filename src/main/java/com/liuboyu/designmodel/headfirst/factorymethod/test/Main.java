package com.liuboyu.designmodel.headfirst.factorymethod.test;

import com.liuboyu.designmodel.headfirst.factorymethod.Pizza;
import com.liuboyu.designmodel.headfirst.factorymethod.PizzaStore;
import com.liuboyu.designmodel.headfirst.factorymethod.store.NYPizzaStore;

/**
 * Created by Tony on 9/17/16.
 */
public class Main {
    public static void main(String[] args) {
        // 到纽约披萨店购买一个纽约风味的奶酪披萨
        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println(pizza.toString());
    }
}
