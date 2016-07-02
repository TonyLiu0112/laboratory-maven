package com.liuboyu.jdk8;

import java.util.List;

/**
 * 艺术家类
 *
 * Created by Tony on 3/31/16.
 */
public class Artist {

    /**
     * 姓名
     */
    private String name;

    /**
     * 成员数量,如果是乐队可能就是多个成员了
     */
    private List<String> members;

    /**
     * 国籍
     */
    private String origin;



    public Artist(String name, String origin) {
        this.name = name;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
