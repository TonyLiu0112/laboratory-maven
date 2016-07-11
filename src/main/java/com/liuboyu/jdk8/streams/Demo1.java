package com.liuboyu.jdk8.streams;

import com.liuboyu.jdk8.Album;
import com.liuboyu.jdk8.Artist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk8 stream操作
 * <p>
 * Created by Tony on 3/31/16.
 */
public class Demo1 {

    public static void main(String[] args) {
//        System.out.println(addUp(Stream.of(1, 2, 3, 4, 5)));

//        System.out.println(showNameCountry(Stream.of(new Artist("Liuboyu", "China"), new Artist("Tony", "USA")).collect(Collectors.toList())));

//        Album album1 = new Album();
//        Album album2 = new Album();
//        Album album3 = new Album();
//        List<Track> s1 = new ArrayList<Track>() {{
//            add(new Track());
//            add(new Track());
//            add(new Track());
//            add(new Track());
//        }};
//        List<Track> s2 = new ArrayList<Track>() {{
//            add(new Track());
//            add(new Track());
//        }};
//        List<Track> s3 = new ArrayList<Track>() {{
//            add(new Track());
//            add(new Track());
//            add(new Track());
//        }};
//        album1.setTracks(s1);
//        album2.setTracks(s2);
//        album3.setTracks(s3);
//        System.out.println(getLess3Music(Stream.of(album1, album2, album3).collect(Collectors.toList())));

        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
            add(10);
        }};

//        System.out.println(map(list.stream(), num -> num * 10));
//        System.out.println(filter(list.stream(), num -> num % 2 == 0));

//        Stream<Integer> s = null;
//        s.map(integer -> );

    }

    /**
     * 求和
     *
     * @param numbers
     * @return
     */
    private static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, element) -> acc + element);
    }

    /**
     * 接收一个艺术家列表为参数,返回艺术家的名字和国籍的字符串
     *
     * @param artistList
     * @return
     */
    private static List<String> showNameCountry(List<Artist> artistList) {
        return artistList.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getOrigin())).collect(Collectors.toList());
    }

    /**
     * 接收一个专辑列表为参数,返回专辑中的曲目数小于等于3首歌的专辑
     *
     * @param albumList
     * @return
     */
    private static List<Album> getLess3Music(List<Album> albumList) {
        return albumList.stream().filter(album -> album.getTracks().size() <= 3).collect(Collectors.toList());
    }

    /**
     * 返回所有艺术家(乐队)中的所有的成员总数
     *
     * @param artists
     * @return
     */
    private static long memberCount(List<Artist> artists) {
        return artists.stream().map(artist -> artist.getMembers().count()).count();
    }

    /**
     * 计算一个字符串中小写字母的个数
     *
     * @param word
     */
    private static int lowerCount(String word) {
        return (int) word.chars().filter(Character::isLowerCase).count();
    }

    /**
     * 在一个字符串列表中, 找出包含最多小写字母的字符串. 对于空列表, 则返回Optional<String>对象
     *
     * @param words
     * @return
     */
    private Optional<String> getMostLowerWord(List<String> words) {
        return words.stream().max(Comparator.comparing(Demo1::lowerCount));
    }

    /**
     * 只用reduce和Lambda表达式写出实现Stream上的map操作的代码, 如果不想返回Stream, 可以返回一个List;
     *
     * @param stream
     * @param fun
     */
    private static <T, R> List<R> map(Stream<T> stream, Function<T, R> fun) {
        return stream.reduce(new ArrayList<R>(), (acc, rs) -> {
            acc.add(fun.apply(rs));
            return acc;
        }, (List<R> left, List<R> right) -> {
            List<R> leftList = new ArrayList<>(left);
            leftList.addAll(right);
            return leftList;
        });
    }

    /**
     * 只用reduce和Lambda表达式写出实现Stream上的filter操作的代码, 如果不想返回stream, 可以返回一个List;
     *
     * @param stream
     * @param <T>
     * @return
     */
    private static <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
        return stream.reduce(new ArrayList<T>(), (acc, rs) -> {
            List<T> list = new ArrayList<>(acc);
            if (predicate.test(rs)) {
                list.add(rs);
            }
            return list;
        }, (List<T> left, List<T> right) -> {
            left.addAll(right);
            return left;
        });
    }

}
