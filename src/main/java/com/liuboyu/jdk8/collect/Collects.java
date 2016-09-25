package com.liuboyu.jdk8.collect;

import com.liuboyu.jdk8.Album;
import com.liuboyu.jdk8.Artist;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * 收集器相关
 * Created by Tony on 7/10/16.
 */
public class Collects {

    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * 找出成员最多的乐队
     *
     * @param stream
     * @return
     */
    public Optional<Artist> biggestGroup(Stream<Artist> stream) {
        Function<Artist, Long> getCount = artist -> artist.getMembers().count();
        return stream.collect(maxBy(comparing(getCount)));
    }

    /**
     * 找出一组专辑上曲目的平均数
     *
     * @param albums
     * @return
     */
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream().collect(averagingInt(album -> album.getTracks().size()));
    }

    /**
     * 组合收集器
     * 在groupingBy收集器中又使用了counting收集器
     *
     * @param albums
     * @return
     */
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(), counting()));
    }

}
