package com.liuboyu.generic;

import java.util.HashSet;
import java.util.Set;

/**
 * 泛型方法
 *
 * Created by Tony on 2/14/16.
 */
public class MyMethod {


    public static void test(Set<? super MyBox> s) {

    }

    public static void main(String[] args) {
        Set<? extends Box> s = new HashSet<>();
        MyBox m = new MyBox();
        MyGinterfaces<Box> myGinterfaces = new MyGinterfaces(new Box());
    }

}
