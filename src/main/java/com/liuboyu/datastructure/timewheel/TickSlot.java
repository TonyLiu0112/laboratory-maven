package com.liuboyu.datastructure.timewheel;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 时间轮刻度槽
 *
 * @author Tony
 */
@Data
public class TickSlot {

    private List<DelayTask> tasks = new LinkedList<>();

    public boolean add(DelayTask task) {
        return tasks.add(task);
    }

    public boolean remove(DelayTask task) {
        return tasks.remove(task);
    }

}
