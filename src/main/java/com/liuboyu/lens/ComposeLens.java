package com.liuboyu.lens;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ComposeLens<Outer, Inner, Value> implements Lens<Outer, Value> {

    private Lens<Outer, Inner> outer;
    private Lens<Inner, Value> inner;

    @Override
    public Value get(Outer o) {
        return inner.get(outer.get(o));
    }

    @Override
    public Outer set(Outer o, Value value) {
        return outer.set(o, inner.set(outer.get(o), value));
    }
}
