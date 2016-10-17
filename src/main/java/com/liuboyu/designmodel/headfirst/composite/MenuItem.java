package com.liuboyu.designmodel.headfirst.composite;

import java.util.Iterator;

/**
 * 菜单项目
 * <p>
 * Created by Tony on 16/10/2016.
 */
public class MenuItem implements MenuComponent {

    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.println(" " + getName());
        if (isVegetarian()) {
            System.out.println("(v)");
        }
        System.out.println(", " + getPrice());
        System.out.println("  -- " + getDescription());
    }

    @Override
    public Iterator createIterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }
}
