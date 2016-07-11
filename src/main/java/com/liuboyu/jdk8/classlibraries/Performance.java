package com.liuboyu.jdk8.classlibraries;

import com.liuboyu.jdk8.Artist;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类库相关
 * <p>
 * Created by Tony on 4/2/16.
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    /**
     * 在该接口基础上, 添加getAllMusicians方法, 该方法返回包含所有艺术家名字的Stream, 如果对象是乐队, 则返回每个乐队成员的名字.
     * 例如: 如果getMusicians返回的是甲壳虫乐队, 则getAllMusicians返回乐队名和乐队成员, 如约翰、保罗等.
     *
     * @param stream
     * @return
     */
    default Stream<List<String>> getAllMusicians(Stream<Artist> stream) {
        return stream.map(artist -> artist.getMembers().collect(Collectors.toList()));
    }

}
