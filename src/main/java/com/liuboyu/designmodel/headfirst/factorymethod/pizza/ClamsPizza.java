package com.liuboyu.designmodel.headfirst.factorymethod.pizza;

import com.liuboyu.designmodel.headfirst.factorymethod.Pizza;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.PizzaIngredientFactory;

/**
 * 蛤蜊披萨
 * <p>
 * Created by Tony on 9/17/16.
 */
public class ClamsPizza extends Pizza {

    private PizzaIngredientFactory pizzaIngredientFactory;

    public ClamsPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Prepare " + name);
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
        clams = pizzaIngredientFactory.createClams();
    }
}
