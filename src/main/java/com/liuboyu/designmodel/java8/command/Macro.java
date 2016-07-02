package com.liuboyu.designmodel.java8.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 宏
 * <p>
 * Created by Tony on 4/13/16.
 */
public class Macro {

    private List<Action> actions;

    public Macro() {
        this.actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }
}
