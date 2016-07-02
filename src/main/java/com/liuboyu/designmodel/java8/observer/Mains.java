package com.liuboyu.designmodel.java8.observer;

/**
 * Created by Tony on 4/16/16.
 */
public class Mains {

    public static void main(String[] args) {
        Moon moon = new Moon();
        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("We made it!");
        });
        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("They're distracted, lets invade earth!");
        });

        moon.land("An asteroid");
        moon.land("Apollo");
    }

}
