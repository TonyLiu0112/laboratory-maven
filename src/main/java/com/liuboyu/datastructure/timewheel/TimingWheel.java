package com.liuboyu.datastructure.timewheel;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimingWheel {

    private TickSlot[] slots;
    private int wheelSize = 10;
    private int offset;

    public TimingWheel() {
        slots = new TickSlot[wheelSize];
        offset = 0;
        for (int i = 0; i < wheelSize; i++) {
            slots[i] = new TickSlot();
        }
    }

    public void add(DelayTask delayTask) {
        long execTime = delayTask.execTime();
        Tuple2<Integer, Integer> location = positionTick(execTime);
        delayTask.setLayer(location.getT1());
        slots[location.getT2()].add(delayTask);
    }

    public void scroll() {
        offset++;
        if (offset == wheelSize)
            offset = 0;
        TickSlot slot = slots[offset];
        Map<Boolean, List<DelayTask>> taskGroup = slot.getTasks().stream().peek(task -> task.setLayer(task.getLayer() - 1)).collect(Collectors.groupingBy(task -> task.getLayer() < 0));
        if (taskGroup.get(false) != null)
            slot.setTasks(taskGroup.get(false));
        taskGroup.getOrDefault(true, Collections.emptyList()).forEach(TaskExecutor::publishTask);
    }

    private Tuple2<Integer, Integer> positionTick(long execTime) {
        return Tuples.of((int) (execTime / wheelSize), (int) (execTime % wheelSize));
    }


}
