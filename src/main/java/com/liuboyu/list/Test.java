package com.liuboyu.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<SubModel> list = new ArrayList<>();
        list.add(new SubModel("ttt"));
        test(list);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    private static class SubModel extends Model {
        private String name;
    }

    private static void test(List<? extends Model> list) {
        System.out.println(list.get(0));
    }

}
