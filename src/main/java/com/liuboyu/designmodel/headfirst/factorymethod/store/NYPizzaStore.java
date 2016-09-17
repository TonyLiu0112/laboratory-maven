package com.liuboyu.designmodel.headfirst.factorymethod.store;

import com.liuboyu.designmodel.headfirst.factorymethod.Pizza;
import com.liuboyu.designmodel.headfirst.factorymethod.PizzaStore;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.PizzaIngredientFactory;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.factory.NYIngredientFactory;
import com.liuboyu.designmodel.headfirst.factorymethod.pizza.CheesePizza;
import com.liuboyu.designmodel.headfirst.factorymethod.pizza.ClamsPizza;

/**
 * 纽约披萨店
 * <p>
 * Created by Tony on 9/17/16.
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        PizzaIngredientFactory pizzaIngredientFactory = new NYIngredientFactory();
        if (type.equals("cheese")) {
            pizza = new CheesePizza(pizzaIngredientFactory);
            pizza.setName("纽约店-cheese披萨");
        } else {
            pizza = new ClamsPizza(pizzaIngredientFactory);
            pizza.setName("纽约点-蛤蜊披萨");
        }
        return pizza;
    }
}
