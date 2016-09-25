package com.liuboyu.designmodel.headfirst.command.test;

import com.liuboyu.designmodel.headfirst.command.Light;
import com.liuboyu.designmodel.headfirst.command.RemoteControl;
import com.liuboyu.designmodel.headfirst.command.light.LightOffCommand;
import com.liuboyu.designmodel.headfirst.command.light.LightOnCommand;

/**
 * Created by Tony on 9/25/16.
 */
public class Main {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        remoteControl.setOnCommand(new LightOnCommand(light));
        remoteControl.setOffCommand(new LightOffCommand(light));

        remoteControl.onButtonWasPushed();
        remoteControl.offButtonWasPushed();
    }
}
