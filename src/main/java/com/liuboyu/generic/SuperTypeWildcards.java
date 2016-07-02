package com.liuboyu.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变
 * <p>
 * Created by Tony on 4/14/16.
 */
public class SuperTypeWildcards {

    /**
     * 这里的super有两层含义:
     * 1. 对参数的限制,必须以Apple的父类参数类型传递进来才合法
     * 2. 对集合使用的限制, 必须为apple本身或其子类才行
     *
     * @param apples
     */
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
//        apples.add(new Fruit());
    }

    /**
     * 这里的extends关键字仅仅限定了参数传递类型必须为Fruit的子类, 并不能对这个集合做任何元素放入的操作
     * 下列情况除了null可以通过,其他全部编译错误;但是null又是一个无意义的操作.
     *
     * @param fruits
     */
    static void writeTo2(List<? extends Fruit> fruits) {
//        fruits.add(new Apple()); // 编译错误
//        fruits.add(new Fruit()); // 编译错误
//        fruits.add(new Object());// 编译错误
//        fruits.add(null); // OK 但无意义
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        writeTo2(list);
        writeTo(list);
        System.out.println(Thread.currentThread().getId() + "我是日志");
    }

}
