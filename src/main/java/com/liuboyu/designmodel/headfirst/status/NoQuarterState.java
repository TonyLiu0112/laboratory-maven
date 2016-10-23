package com.liuboyu.designmodel.headfirst.status;

/**
 * Created by Tony on 22/10/2016.
 */
public class NoQuarterState implements State {

    private MachineContext machineContext;

    public NoQuarterState(MachineContext machineContext) {
        this.machineContext = machineContext;
    }

    @Override
    public void insertQuarter() {
        System.out.println("成功投币！");
        machineContext.setCrtState(machineContext.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("不能退币，您还没投币，请先投币！");
    }

    @Override
    public void tumCrank() {
        System.out.println("按钮被按下了，但是没有获得糖果");
    }

    @Override
    public void dispense() {
        System.out.println("您需要先支付!");
    }
}
