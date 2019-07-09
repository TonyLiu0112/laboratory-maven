package com.liuboyu.lens;

public interface Lens<O, V> {

    V get(O o);

    O set(O o, V v);

}
