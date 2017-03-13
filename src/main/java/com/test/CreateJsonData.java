package com.test;

import com.alibaba.fastjson.JSONObject;

import java.util.Random;

/**
 * 生成json测试数据
 * <p>
 * Created by Tony on 27/11/2016.
 */
public class CreateJsonData {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String json = JSONObject.toJSONString(new People("Tony" + i, i, random.nextInt(100)));
            System.out.println(json);
        }
    }

}

class People {
    private String name;
    private int age;
    private double score;

    public People(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
