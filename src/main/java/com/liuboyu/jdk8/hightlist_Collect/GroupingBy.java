package com.liuboyu.jdk8.hightlist_Collect;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 用一个定制的收集器实现Collectors.groupingBy方法, 不需要提供一个下游收集器, 只需实现一个最简单的即可.
 * 别看JDK源码, 这是作弊! 提示: 可从下面这行代码开始:
 * public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>
 * <p>
 * Created by Tony on 4/8/16.
 */
public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    /**
     * 创建容器的工厂
     *
     * @return
     */
    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    /**
     * 收集器 将元素不断叠加到收集器中
     *
     * @return
     */
    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (kListMap, element) -> {
            K key = classifier.apply(element);
            List<T> elements = kListMap.computeIfAbsent(key, k -> new ArrayList<>());
            elements.add(element);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
