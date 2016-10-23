package com.liuboyu.designmodel.headfirst.status;

/**
 * Created by Tony on 22/10/2016.
 */
public class MachineContext {

    State soldOutState;                 // 售罄状态
    State noQuarterState;               // 未投币状态
    State hasQuarterState;              // 有投币状态
    State soldState;                    // 已销售状态

    int count = 0;                      // 糖果总数
    State crtState = noQuarterState;    // 当前状态

    public MachineContext(int count) {
        this.count = count;
    }

    public void releaseBall() {
        System.out.println("购买成功！已将一个糖果放到槽口中，请拿取");
        if (count != 0)
            count--;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }

    public void setCrtState(State crtState) {
        this.crtState = crtState;
    }
}
