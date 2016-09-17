package com.liuboyu.designmodel.headfirst.factorymethod.ingredient.factory;

import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.*;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.chicago.FrozenClams;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.chicago.MozzarellaCheese;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.chicago.PlumTomatoSauce;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.chicago.ThickCrustDough;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.ny.Garlic;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.ny.Onion;
import com.liuboyu.designmodel.headfirst.factorymethod.ingredient.ny.SlicedPepperoni;

/**
 * 芝加哥地区的原料工厂类
 * <p>
 * Created by Tony on 9/17/16.
 */
public class ChicagoIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
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
        return new FrozenClams();
    }
}
