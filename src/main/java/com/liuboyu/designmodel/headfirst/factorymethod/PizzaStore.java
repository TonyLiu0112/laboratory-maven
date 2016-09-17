package com.liuboyu.designmodel.headfirst.factorymethod;

/**
 * 披萨店的抽象
 * <p>
 * Created by Tony on 9/17/16.
 */
public abstract class PizzaStore {

    /**
     * 披萨店下订单需要做的事儿
     *
     * @param type
     * @return
     */
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 这个就是一个工厂方法(具体的实现交给子类决定，依赖倒置)
     *
     * @param type
     * @return
     */
    public abstract Pizza createPizza(String type);
}
