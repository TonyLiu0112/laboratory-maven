package com.liuboyu.lens;

import com.liuboyu.lens.adt.Address;

public class AddressNoLens implements Lens<Address, String> {

    @Override
    public String get(Address o) {
        return o.getNo();
    }

    @Override
    public Address set(Address o, String s) {
        o.setNo(s);
        return o;
    }
}
