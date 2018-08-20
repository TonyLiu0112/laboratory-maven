package com.liuboyu.classloader;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader parent = classLoader.getParent();

        // application class loader: load class of classpath:*
        System.out.println(classLoader);
        // ext class loader: load class of $JAVA_HOME/lib/ext/*
        System.out.println(parent);

        Object c1 = loadCar(classLoader);
        Object c2 = loadCar(parent);

        System.out.println(c1 instanceof Car);
        System.out.println(c2 instanceof Car);

    }

    private static Object loadCar(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = classLoader.loadClass("com.liuboyu.classloader.Car");
        return clazz.newInstance();
    }

}
