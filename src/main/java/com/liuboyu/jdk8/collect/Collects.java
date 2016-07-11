package com.liuboyu.jdk8.collect;

import com.liuboyu.jdk8.Artist;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

/**
 * 收集器相关
 * Created by Tony on 7/10/16.
 */
public class Collects {

    public static void main(String[] args) {
        System.out.println();
    }

    public Optional<Artist> biggestGroup(Stream<Artist> stream) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return stream.collect(maxBy(comparing(getCount)));
    }


}
