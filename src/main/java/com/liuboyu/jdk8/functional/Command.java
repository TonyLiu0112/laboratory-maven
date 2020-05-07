package com.liuboyu.jdk8.functional;

@FunctionalInterface
public interface Command<T, R> {

    R accept(T t);

    default <V> Command<T, V> andThen(Command<? super T, V> target) {
        return t -> {
            R r = accept(t);
            return target.accept(t);
        };
    }

}
