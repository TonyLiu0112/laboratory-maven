package com.liuboyu.designmodel.headfirst.status;

/**
 * Created by Tony on 22/10/2016.
 */
public class SoldOutState implements State {

    private MachineContext machineContext;

    public SoldOutState(MachineContext machineContext) {
        this.machineContext = machineContext;
    }

    @Override
    public void insertQuarter() {
        System.out.println("对不起，糖果已卖光，不能在投币了！");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("您还未投币，不能退币！");
    }

    @Override
    public void tumCrank() {
        System.out.println("您按下了获取按钮，但是机器没糖果了！");
    }

    @Override
    public void dispense() {
        System.out.println("部门获取糖果，机器已经没糖果了");
    }
}
