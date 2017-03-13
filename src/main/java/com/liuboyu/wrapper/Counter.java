package com.liuboyu.wrapper;

import java.util.Collection;
import java.util.Set;

/**
 * Test
 * <p>
 * Created by Tony on 13/03/2017.
 */
public class Counter<E> extends SetWrapper<E> {

    private int count = 0;

    public Counter(Set<E> set) {
        super(set);
    }

    @Override
    public boolean add(E s) {
        count++;
        return super.add(s);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        return super.addAll(c);
    }

    public int getCount() {
        return count;
    }
}
