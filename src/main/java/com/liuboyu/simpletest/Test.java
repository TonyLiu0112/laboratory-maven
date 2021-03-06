package com.liuboyu.simpletest;

public class Test {

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.getIndex());
        System.out.println(System.getProperty("java.io.tmpdir"));
    }

}

class A {

    private int index = 20;
    String[] item;

    public A() {
        item = new String[getIndex()];
    }

    public int getIndex() {
        return index;
    }

}

class B extends A {

    private int index = 2;

    @Override
    public int getIndex() {
        return index;
    }

}
