package com.test;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Tony on 3/21/16.
 */
public class Test {

    public static void main(String[] args) {
//        List<JsonObj> list = new ArrayList<>();
//        IntStream.range(0, 10).forEach(value -> {
//            list.add(new JsonObj("liuboyu" + value, value, "描述" + value));
//        });
//        System.out.println(JSONObject.toJSONString(list));
    }

}

class JsonObj {

    private String name;

    private int age;

    private String desc;

    public JsonObj(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public JsonObj setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public JsonObj setAge(int age) {
        this.age = age;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public JsonObj setDesc(String desc) {
        this.desc = desc;
        return this;
    }

}