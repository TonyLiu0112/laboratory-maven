package com.liuboyu.datastructure.timewheel;

import lombok.Data;
import org.apache.commons.collections.MapUtils;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 时间轮
 * <p>
 * 当前时间轮为秒级的刻度槽
 *
 * @author Tony
 */
public class TimingWheel {

    private TickSlot[] slots;
    private final int wheelSize;
    private TwPointer pointer;

    public TimingWheel() {
        this(10);
    }

    public TimingWheel(int wheelSize) {
        this.wheelSize = wheelSize;
        pointer = new TwPointer();
        slots = new TickSlot[this.wheelSize];
        for (int i = 0; i < this.wheelSize; i++) {
            slots[i] = new TickSlot();
        }
    }

    /**
     * 注册一个延时任务
     *
     * @param delayTask 等待执行的延时任务
     */
    public void register(DelayTask delayTask) {
        long execTime = delayTask.execTime();
        Tuple2<Integer, Integer> location = positionTick(execTime);
        delayTask.setLayer(location.getT1());
        slots[location.getT2()].add(delayTask);
    }

    /**
     * 时间轮向后滚动一个刻度
     */
    public void scroll() {
        pointer.move();
        TickSlot slot = slots[pointer.offset];
        Map<Boolean, List<DelayTask>> taskGroup = slot.getTasks().stream().peek(task -> task.setLayer(task.getLayer() - 1)).collect(Collectors.groupingBy(task -> task.getLayer() < 0));
        if (MapUtils.isEmpty(taskGroup))
            return;
        slot.setTasks(taskGroup.getOrDefault(false, new LinkedList<>()));
        taskGroup.getOrDefault(true, new LinkedList<>()).forEach(TaskExecutor::publishTask);
    }

    /**
     * 根据执行时间, 计算出此时间所在时间轮的层数与slot
     *
     * @param execTime 执行时间(s)
     * @return tuple(层数, slot)
     */
    private Tuple2<Integer, Integer> positionTick(long execTime) {
        if (execTime <= pointer.timeLine)
            throw new RuntimeException("执行时间:" + execTime + " 不能小于当前时间:" + pointer.timeLine + ".");
        long targetSlot = execTime - pointer.timeLine + pointer.offset;
        return Tuples.of((int) (targetSlot / wheelSize), (int) (targetSlot % wheelSize));
    }

    @Data
    private class TwPointer {
        private int offset = 0;
        private long timeLine = TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        private void move() {
            offset++;
            if (offset == wheelSize)
                offset = 0;
            timeLine++;
        }
    }

}
