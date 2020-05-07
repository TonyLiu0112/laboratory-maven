package com.liuboyu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Metadata {
        private List<String> tracks = new ArrayList<>();
        private String track;
        private String begin;
        private String end;
        private int deep = 0;

        public String toString() {
            return tracks.stream().map(s -> s.split(",")[0]).collect(Collectors.joining(","));
        }
    }

    private static Set<Metadata> result = new HashSet<>();
    private static Map<String, List<Metadata>> endTrackMap = new HashMap<>();
    private static Map<String, List<Metadata>> repeatedMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
//        Stream.of(
//                "1,2",
//                "1,3",
//                "2,4",
//                "3,4",
//                "4,1",
//                "2,5",
//                "3,5",
//                "5,4",
//                "4,6",
//                "6,7",
//                "7,4").forEach(Test::process);
        FileUtils.readLines(new File("/Users/Tony/Documents/workspaces/workspace/laboratory-maven/src/main/resources/test_data.txt"), "UTF-8").forEach(Test::process);

        result.stream()
                .sorted(Comparator.comparing(Metadata::getDeep)
                        .thenComparing(metadata -> metadata.getBegin().length())
                        .thenComparing(Metadata::toString))
                .map(Metadata::toString)
                .forEach(System.out::println);
    }

    private static void process(String flow) {
        String[] accts = flow.split(",");
        String begin = accts[0];
        String end = accts[1];
        if (endTrackMap.containsKey(begin)) {
            drawTrack(endTrackMap, flow, begin, end);
            if (repeatedMap.containsKey(begin)) {
                drawTrack(repeatedMap, flow, begin, end);
            }
        } else {
            Metadata metadata = new Metadata(new ArrayList<String>() {{
                add(flow);
            }}, flow, begin, end, 1);
            if (!endTrackMap.containsKey(end)) {
                endTrackMap.put(end, Stream.of(metadata).collect(Collectors.toList()));
            } else {
                repeatedMap.put(end, Stream.of(metadata).collect(Collectors.toList()));
            }
        }
    }

    private static void drawTrack(Map<String, List<Metadata>> endTrackMap, String flow, String begin, String end) {
        List<Metadata> hisTracks = endTrackMap.get(begin);
        Iterator<Metadata> iterator = hisTracks.iterator();
        while (iterator.hasNext()) {
            Metadata metadata = iterator.next();
            metadata.deep += 1;
            metadata.track += " -> " + flow;
            metadata.tracks.add(flow);
            if (equalsFirst(metadata, end) || equalsMiddle(metadata.tracks, end)) {
                iterator.remove();
            } else {
                metadata.end = end;
            }
        }
        endTrackMap.remove(begin);
        if (!hisTracks.isEmpty()) {
            List<Metadata> orDefault = endTrackMap.getOrDefault(end, new ArrayList<>());
            orDefault.addAll(hisTracks);
            endTrackMap.put(end, orDefault);
        }
    }

    private static boolean equalsFirst(Metadata metadata, String end) {
        if (end.equals(metadata.begin)) {
            if (metadata.deep >= 3 && metadata.deep < 7) {
                result.add(metadata);
                return true;
            }
        }
        return false;
    }

    private static boolean equalsMiddle(List<String> tracks, String end) {
        Metadata metadata = new Metadata();
        for (int i = tracks.size() - 1; i >= 0; i--) {
            String flow = tracks.get(i);
            metadata.deep += 1;
            metadata.tracks.add(flow);
            String crtBegin = flow.split(",")[0];
            if (crtBegin.equalsIgnoreCase(end) && metadata.deep >= 3 && metadata.deep < 7) {
                Collections.reverse(metadata.tracks);
                metadata.track = String.join("->", metadata.tracks);
                metadata.begin = crtBegin;
                metadata.end = end;
                result.add(metadata);
                return true;
            }
        }
        return false;
    }

}
