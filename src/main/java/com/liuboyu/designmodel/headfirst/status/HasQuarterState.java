package com.liuboyu.designmodel.headfirst.status;

/**
 * Created by Tony on 22/10/2016.
 */
public class HasQuarterState implements State {

    private MachineContext machineContext;

    public HasQuarterState(MachineContext machineContext) {
        this.machineContext = machineContext;
    }

    @Override
    public void insertQuarter() {
        System.out.println("机器中已经有硬币了");
    }

    @Override
    public void ejectQuarter() {
        machineContext.setCrtState(machineContext.getNoQuarterState());
        System.out.println("退币成功");
    }

    @Override
    public void tumCrank() {
        System.out.println("您已按下按钮！");
        machineContext.setCrtState(machineContext.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("请先按下按钮在发放");
    }
}
