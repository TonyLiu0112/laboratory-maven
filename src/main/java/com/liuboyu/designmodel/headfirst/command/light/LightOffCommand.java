package com.liuboyu.designmodel.headfirst.command.light;

import com.liuboyu.designmodel.headfirst.command.Command;
import com.liuboyu.designmodel.headfirst.command.Light;

/**
 * Created by Tony on 9/25/16.
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void exec() {
        light.off();
    }
}
