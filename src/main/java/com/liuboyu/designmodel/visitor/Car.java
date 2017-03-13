package com.liuboyu.designmodel.visitor;

/**
 * Created by Tony on 13/03/2017.
 */
public class Car {

    private Engine engine = new Engine();
    private Body body = new Body();
    private Wheel[] wheels = {new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left"), new Wheel("back right")};

    void accept(Visitor printVisitor) {
        printVisitor.visit(this);
        engine.accept(printVisitor);
        body.accept(printVisitor);
        for (int i = 0; i < wheels.length; ++i)
            wheels[i].accept(printVisitor);
    }

}
