package com.liuboyu.lens.adt;

import lombok.Data;

@Data
public class Customer {

    private Integer id;

    private String name;

    private Address address;

}
