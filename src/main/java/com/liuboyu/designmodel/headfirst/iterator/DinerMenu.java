package com.liuboyu.designmodel.headfirst.iterator;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 餐厅的一个菜单
 * 使用的是数组来维护菜单
 * <p>
 * Created by Tony on 16/10/2016.
 */
public class DinerMenu {
    private int offset = 0;
    private MenuItem[] menuItems;
    private final int size = 3;

    public DinerMenu() {
        menuItems = new MenuItem[size];
        addItem("diner1", "diner1 description", true, 2.99);
        addItem("diner2", "diner2 description", false, 3.99);
        addItem("diner3", "diner3 description", true, 5.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (offset >= menuItems.length) {
            System.err.println("Menu is full!");
        } else {
            menuItems[offset] = menuItem;
            offset++;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public class Itr implements Iterator<MenuItem> {
        private int cursor;
        private int lastRet;
        private int expectedModCount;

        public Itr() {
            this.lastRet = -1;
            this.expectedModCount = DinerMenu.this.size;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != DinerMenu.this.size;
        }

        @Override
        public MenuItem next() {
            this.checkForComodification();
            int var1 = this.cursor;
            if (var1 >= DinerMenu.this.size) {
                throw new NoSuchElementException();
            } else {
                MenuItem[] items = DinerMenu.this.menuItems;
                if (var1 >= items.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = var1 + 1;
                    return items[this.lastRet = var1];
                }
            }
        }

        final void checkForComodification() {
            if (DinerMenu.this.size != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public Iterator<MenuItem> iterator() {
        return new Itr();
    }

    public static void main(String[] args) {
        DinerMenu menu = new DinerMenu();
        Iterator<MenuItem> itr = menu.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().getName());
        }
    }

}
