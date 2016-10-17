package com.liuboyu.designmodel.headfirst.composite;

import java.util.Iterator;

/**
 * 菜单组件，提供一组基本的操作
 * <p>
 * Created by Tony on 16/10/2016.
 */
public interface MenuComponent {

    default void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException("unsupport add");
    }

    default void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException("unsupport ops");
    }

    default MenuComponent getChild(int i) {
        throw new UnsupportedOperationException("unsupport getChild");
    }

    default String getName() {
        throw new UnsupportedOperationException("unsupport get name");
    }

    default  String getDescription() {
        throw new UnsupportedOperationException("unsupport get description");
    }

    default double getPrice() {
        throw new UnsupportedOperationException("unsupport get price");
    }

    default boolean isVegetarian() {
        throw new UnsupportedOperationException("unsupport is vegetarian");
    }

    default void print(){
        throw new UnsupportedOperationException("unsupport print");
    }

    default Iterator createIterator() {
        throw new UnsupportedOperationException("unsupport iterator");
    }

}
