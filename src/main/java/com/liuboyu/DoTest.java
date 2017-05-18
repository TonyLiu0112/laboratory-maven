package com.liuboyu;

import com.liuboyu.common.ConvertUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tony on 3/5/16.
 */
public class DoTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        DemoModel source = new DemoModel();
        source.setName("liuboyu");

        List<DemoModel> sourceList = new ArrayList<>();
        sourceList.add(source);

        List<TargetMdoel> targetList = ConvertUtils.convert(sourceList, TargetMdoel.class);

        targetList.forEach(System.out::println);

    }

    public static class TargetMdoel {
        private String name;
        private int age;
        private Date time;

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

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }

    public static class DemoModel {
        private String name;
        private int age;
        private Date time;

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

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }

}
