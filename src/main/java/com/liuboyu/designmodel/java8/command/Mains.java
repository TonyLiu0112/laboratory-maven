package com.liuboyu.designmodel.java8.command;

/**
 * 程序入口类
 * <p>
 * Created by Tony on 4/13/16.
 */
public class Mains {

    public static void main(String[] args) {
        Editor editor = new GUIEditor();
        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }

}
