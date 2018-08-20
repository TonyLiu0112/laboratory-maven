package com.liuboyu.classloader;

public class TonyTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        TonyClassLoader tonyClassLoader = new TonyClassLoader();
        Class<?> myCarClazz = Class.forName("com.liuboyu.classloader.Car", true, tonyClassLoader);

        Object myCar = myCarClazz.newInstance();

        Class<?> appCarClazz = Thread.currentThread().getContextClassLoader().loadClass("com.liuboyu.classloader.Car");
        Object appCar = appCarClazz.newInstance();

        System.out.println(myCar instanceof Car);
        System.out.println(appCar instanceof Car);

    }

}
