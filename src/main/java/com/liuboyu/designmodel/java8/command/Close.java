package com.liuboyu.designmodel.java8.command;

/**
 * Created by Tony on 4/13/16.
 */
public class Close implements Action {

    private final Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.close();
    }
}
