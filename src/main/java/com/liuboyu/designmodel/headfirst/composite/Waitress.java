package com.liuboyu.designmodel.headfirst.composite;

import java.util.Iterator;

/**
 * 服务员对象
 * <p>
 * Created by Tony on 16/10/2016.
 */
public class Waitress {

    MenuComponent allMenu;

    public Waitress(MenuComponent allMenu) {
        this.allMenu = allMenu;
    }

    public void printMenu() {
        allMenu.print();
    }

    public void printVmenu() {
        Iterator<MenuComponent> iterator = allMenu.createIterator(); // 获得菜单的迭代器，这个迭代器是CompositeIterator对象
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            System.out.println(menuComponent.getName());
            try {
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e) {
            }
        }
    }
}
