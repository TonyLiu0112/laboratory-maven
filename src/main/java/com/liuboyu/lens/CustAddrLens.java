package com.liuboyu.lens;

import com.liuboyu.lens.adt.Address;
import com.liuboyu.lens.adt.Customer;

public class CustAddrLens implements Lens<Customer, Address> {
    @Override
    public Address get(Customer o) {
        return o.getAddress();
    }

    @Override
    public Customer set(Customer o, Address address) {
        o.setAddress(address);
        return o;
    }
}
