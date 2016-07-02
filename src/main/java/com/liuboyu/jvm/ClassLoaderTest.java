package com.liuboyu.jvm;

/**
 * Created by Tony on 3/21/16.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader myClasssLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                return super.loadClass(name);
            }
        };

    }

}
