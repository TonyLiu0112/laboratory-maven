package com.liuboyu.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 4/13/16.
 */
public class FruitDemo {
//    public static void Mains(String[] args) {
//        // 仅仅编译阶段可以通过, 运行时编译器将明确类型只能为apple或其子类
//        Fruit[] fruits = new Apple[10];
//        fruits[0] = new Apple();
//        fruits[1] = new Jonathan();
//        fruits[2] = new Fruit();
//        fruits[3] = new Orange();
//    }

    public static void main(String[] args) {
//        List<? extends Fruit> flist = new ArrayList<Apple>();
//        flist.add(new Orange()); // compile error
//        flist.add(new Apple()); // compile error
//        flist.add(new Fruit()); // compile error
//        flist.add(new Object()); // compile error

    }

}

class Fruit{}
class Apple extends Fruit {}
class Orange extends Fruit{}
class Jonathan extends Apple{}


