package com.liuboyu.datastructure.timewheel;

import lombok.Data;

@Data
public abstract class DelayTask implements Runnable {

    private int layer;

    public abstract long execTime();

}
