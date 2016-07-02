package com.liuboyu.jdk8;

import java.util.List;

/**
 * 专辑对象
 * <p>
 * Created by Tony on 3/31/16.
 */
public class Album {

    private String name;

    /**
     * 专辑里的曲目列表
     */
    private List<Track> tracks;

    /**
     * 参与创作专辑的艺术家列表
     */
    private List<Artist> musicians;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Artist> musicians) {
        this.musicians = musicians;
    }
}
