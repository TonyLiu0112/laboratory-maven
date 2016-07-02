package com.liuboyu.designmodel.java8.command;

/**
 * Created by Tony on 4/13/16.
 */
public class Open implements Action {

    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.open();
    }
}
