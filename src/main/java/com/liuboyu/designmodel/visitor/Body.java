package com.liuboyu.designmodel.visitor;

/**
 * Created by Tony on 13/03/2017.
 */
public class Body {

    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
