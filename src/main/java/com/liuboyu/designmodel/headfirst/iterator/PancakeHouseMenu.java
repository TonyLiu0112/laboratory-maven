package com.liuboyu.designmodel.headfirst.iterator;

import java.util.ArrayList;

/**
 * 煎饼屋的菜单
 * 实现上是使用的ArrayList来描述一个菜单
 * <p>
 * Created by Tony on 16/10/2016.
 */
public class PancakeHouseMenu {

    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItem("pancake1", "pancake1 description", true, 2.99);
        addItem("pancake2", "pancake2 description", false, 3.99);
        addItem("pancake3", "pancake3 description", true, 5.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
}
