package com.liuboyu.designmodel.headfirst.command;

/**
 * 控制器客户端
 * <p>
 * Created by Tony on 9/25/16.
 */
public class RemoteControl {

    private Command onCommand;
    private Command offCommand;

    public void setOnCommand(Command onCommand) {
        this.onCommand = onCommand;
    }

    public void setOffCommand(Command offCommand) {
        this.offCommand = offCommand;
    }

    public void onButtonWasPushed() {
        onCommand.exec();
    }

    public void offButtonWasPushed() {
        offCommand.exec();
    }

}
