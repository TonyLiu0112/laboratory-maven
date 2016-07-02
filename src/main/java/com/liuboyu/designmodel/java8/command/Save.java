package com.liuboyu.designmodel.java8.command;

/**
 * Created by Tony on 4/13/16.
 */
public class Save implements Action {

    private final Editor editor;

    public Save(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.save();
    }
}
