package com.liuboyu.designmodel.headfirst.factorymethod.ingredient;

/**
 * 抽象工厂模式的工厂接口，定义了一个产品的抽象
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
