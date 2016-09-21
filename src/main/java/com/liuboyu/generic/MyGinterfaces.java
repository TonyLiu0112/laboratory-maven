package com.liuboyu.generic;

/**
 * Created by Tony on 2/14/16.
 */
public class MyGinterfaces<E extends Box> implements Ginterface<E> {

    public E e;

    public MyGinterfaces(E e) {
        this.e = e;
    }
}
