package com.liuboyu.jdk8.functional;

public class MyTask {

    public static void main(String[] args) {

    }

    public static void merge() {
        Command<String, String> command = s -> "Tony";
        command = command.andThen(s -> "666");
    }

}
