package com.liuboyu.designmodel.headfirst.factorymethod.ingredient;

/**
 * 食材工厂类
 * <p>
 * Created by Tony on 9/17/16.
 */
public interface PizzaIngredientFactory {

    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClams();

}
