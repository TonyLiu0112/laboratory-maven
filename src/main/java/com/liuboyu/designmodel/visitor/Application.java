package com.liuboyu.designmodel.visitor;

/**
 * Created by Tony on 13/03/2017.
 */
public class Application {
    public static void main(String[] args) {
        Car car = new Car();
        car.accept(new PrintVisitor());
    }
}
