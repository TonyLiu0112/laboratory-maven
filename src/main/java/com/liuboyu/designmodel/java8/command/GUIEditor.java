package com.liuboyu.designmodel.java8.command;

/**
 * Created by Tony on 4/13/16.
 */
public class GUIEditor implements Editor {

    @Override
    public void save() {
        System.out.println("保存成功");
    }

    @Override
    public void open() {
        System.out.println("打开成功");
    }

    @Override
    public void close() {
        System.out.println("系统已关闭");
    }
}
