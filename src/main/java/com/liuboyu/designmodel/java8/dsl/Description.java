package com.liuboyu.designmodel.java8.dsl;

/**
 * Created by Tony on 4/17/16.
 */
public class Description {
    private String name;

    public Description(String name) {
        this.name = name;
    }

    public static void describe(String name, Suite suite) {
        Description description = new Description(name);
        suite.specifySuite(description);
    }

    public void should(String description, Specification specification) {
        Expect expect = new Expect();
        specification.specifyBehaviour(expect);
    }


}
