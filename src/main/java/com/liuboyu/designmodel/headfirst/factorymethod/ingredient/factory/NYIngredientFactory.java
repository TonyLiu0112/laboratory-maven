package com.liuboyu.designmodel.headfirst.factorymethod.ingredient.factory;

import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.*;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.ny.*;

/**
 * 纽约地区的原料工厂类(抽象工厂的具体实现)
 * <p>
 * Created by Tony on 9/17/16.
 */
public class NYIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = {new Garlic(), new Onion()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
