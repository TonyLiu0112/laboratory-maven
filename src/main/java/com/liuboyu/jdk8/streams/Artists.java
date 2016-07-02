package com.liuboyu.jdk8.streams;

import com.liuboyu.jdk8.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 重构该类, 使得getArtist方法返回一个Optional<Artist>对象. 如果索引在有效范围之内, 返回对应的元素,否则返回一个空Optional对象.
 * 此外, 还需要重构getArtistName方法, 保持相同的行为
 * <p>
 * Created by Tony on 4/3/16.
 */
public class Artists {

    /* 样板代码

    private List<Artist> artists;

    public Artist getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            indexException(index);
        }
        return artists.get(index);
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + "doesn't correspond to an Artist.");
    }

    public String getArtistName(int index) {
        try {
            Artist artist = getArtists(index);
            return artist.getName();
        } catch (Exception e) {
            return "unknown.";
        }
    }

    */

    public List<Artist> artists = new ArrayList<>();

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public Optional<String> getArtistName(int index) {
        Optional<Artist> optional = getArtist(index);
        if (!optional.isPresent()) return Optional.empty();
        Artist artist = optional.get();
        return Optional.of(artist.getName());
    }

    public static void main(String[] args) {
        Artists artists = new Artists();
        artists.artists.add(new Artist("liuboyu", "中国"));
        artists.artists.add(new Artist("tony", "美国"));

        Optional optional = artists.getArtistName(1);
        if (optional.isPresent())
            System.out.println(optional.get());
        else
            System.out.println("没有合适的值");

    }

}
