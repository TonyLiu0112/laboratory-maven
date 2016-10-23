package com.liuboyu.designmodel.headfirst.status;

/**
 * 已销售状态
 * <p>
 * Created by Tony on 22/10/2016.
 */
public class SoldState implements State {

    private MachineContext machineContext;

    public SoldState(MachineContext machineContext) {
        this.machineContext = machineContext;
    }

    @Override
    public void insertQuarter() {
        System.out.println("我们已经给你了一个糖果了！");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("对不起，您已经操作了购买按钮了！");
    }

    @Override
    public void tumCrank() {
        System.out.println("按下两次购买按钮不能给您两块糖果！");
    }

    @Override
    public void dispense() {
        machineContext.releaseBall();
        if (machineContext.getCount() > 0) {
            machineContext.setCrtState(machineContext.getNoQuarterState());
        } else {
            machineContext.setCrtState(machineContext.getSoldOutState());
        }
    }
}
