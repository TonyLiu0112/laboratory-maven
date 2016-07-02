package com.liuboyu.generic;

/**
 * Created by Tony on 4/13/16.
 */
public class Holder<T> {

    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<>(new Apple());
        Apple d = apple.getValue();
        apple.setValue(d);
        Holder<? extends Fruit> fruit = apple;

    }
}
