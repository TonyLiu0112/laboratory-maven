package com.liuboyu.designmodel.headfirst.composite;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Tony on 16/10/2016.
 */
public class CompsiteIterator implements Iterator<MenuComponent> {

    Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompsiteIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator iterator = stack.peek(); // 获得栈顶元素,但不移除
            if (iterator.hasNext()) {
                return true;
            } else {
                stack.pop(); // 获取并移除栈顶元素
                return hasNext();   // 递归调用，判断栈中的下一个元素
            }

        }
    }

    @Override
    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent component = iterator.next();
            if (component instanceof Menu) {
                stack.push(component.createIterator());
            }
            return component;
        } else {
            return null;
        }
    }
}
