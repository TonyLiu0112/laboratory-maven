package com.liuboyu.designmodel.visitor;

/**
 * Created by Tony on 13/03/2017.
 */
public class Wheel {

    private String name;

    public Wheel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
