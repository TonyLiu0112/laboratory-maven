package com.liuboyu.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class TonyClassLoader extends ClassLoader {

    public TonyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public TonyClassLoader() {
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("/Users/Tony/Documents/workspaces/workspace/laboratory-maven/target/classes/com/liuboyu/classloader/Car.class");
        try {
            byte[] bytes = getClassBytes(file);
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equalsIgnoreCase("com.liuboyu.classloader.Car")) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    private byte[] getClassBytes(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(byteOut);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return byteOut.toByteArray();
    }

}
