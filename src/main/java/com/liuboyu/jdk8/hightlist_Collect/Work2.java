package com.liuboyu.jdk8.hightlist_Collect;

import com.liuboyu.jdk8.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * 找出名字最长的艺术家,分别使用收集器和reduce高阶函数实现.
 * <p>
 * Created by Tony on 4/7/16.
 */
public class Work2 {

    private static Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    /**
     * reduce实现
     *
     * @param artists
     * @return
     */
    static Artist maxLength1(List<Artist> artists) {
        return artists.stream()
                .reduce((artist, artist2) -> byNameLength.compare(artist, artist2) >= 0 ? artist : artist2)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 收集器方式实现
     *
     * @param artists
     * @return
     */
    static Artist maxLength2(List<Artist> artists) {
        return artists.stream().collect(Collectors.maxBy(byNameLength)).orElseThrow(RuntimeException::new);
    }

    public static void main(String[] args) {
        List<Artist> artists = Stream.of(
                new Artist("liuboyu", null),
                new Artist("liuboyu222", null),
                new Artist("liuboyu3344", null)).collect(Collectors.toList());
        System.out.println(maxLength2(artists).getName());
    }

}
