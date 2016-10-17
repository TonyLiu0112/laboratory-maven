package com.liuboyu.designmodel.headfirst.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 菜单项目
 * <p>
 * Created by Tony on 16/10/2016.
 */
public class Menu implements MenuComponent {

    ArrayList<MenuComponent> menuComponents = new ArrayList();

    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
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
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public void print() {
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print(); // 如果是菜单，递归调用，如果是具体菜，直接调用菜的打印方法
        }
    }

    @Override
    public Iterator createIterator() {
        return new CompsiteIterator(menuComponents.iterator());
    }
}
