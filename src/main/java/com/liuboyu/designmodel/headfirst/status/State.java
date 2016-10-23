package com.liuboyu.designmodel.headfirst.status;

/**
 * 状态机器的状态抽象
 *
 * <p>
 * Created by Tony on 22/10/2016.
 */
public interface State {

    /**
     * 投币
     */
    void insertQuarter();

    /**
     * 退币
     */
    void ejectQuarter();

    /**
     * 扭动阀门
     */
    void tumCrank();

    /**
     * 出货
     */
    void dispense();

}
