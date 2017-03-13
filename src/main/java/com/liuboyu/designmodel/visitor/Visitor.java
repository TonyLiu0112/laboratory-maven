package com.liuboyu.designmodel.visitor;

/**
 * Created by Tony on 13/03/2017.
 */
public interface Visitor {

    void visit(Wheel wheel);

    void visit(Engine engine);

    void visit(Car car);

    void visit(Body body);
}
