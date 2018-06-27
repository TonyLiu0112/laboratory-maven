package com.liuboyu.sort;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListSortVersionErrorExample {

    /**
     * 按照两个字段排序
     */
    private static List<Map<String, Object>> orderListByTwoColumn(List<Map<String, Object>> list, final String order, final String dimension, final String dimension2) {
        list.sort((o1, o2) -> {
            System.out.println();
            System.out.println(o1);
            System.out.println(o2);
            Object value1Obj = o1.get(dimension);
            value1Obj = value1Obj == null ? 0 : value1Obj;
            Object value2Obj = o2.get(dimension);
            value2Obj = value2Obj == null ? 0 : value2Obj;
            Float value1 = Float.parseFloat(value1Obj.toString());
            Float value2 = Float.parseFloat(value2Obj.toString());

            if (null != order && order.equals("desc")) {
                if (!value1.equals(value2)) {
                    return value2.compareTo(value1);
                } else {
                    Object value3Obj = o1.get(dimension2);
                    value3Obj = value3Obj == null ? 0 : value3Obj;
                    Object value4Obj = o2.get(dimension2);
                    value4Obj = value4Obj == null ? 0 : value4Obj;
                    Float value3 = Float.parseFloat(value3Obj.toString());
                    Float value4 = Float.parseFloat(value4Obj.toString());
                    return value4.compareTo(value3);
                }
            } else {
                if (!value1.equals(value2)) {
                    return value1.compareTo(value2);
                } else {
                    Object value3Obj = o1.get(dimension2);
                    value3Obj = value3Obj == null ? 0 : value3Obj;
                    Object value4Obj = o2.get(dimension2);
                    value4Obj = value4Obj == null ? 0 : value4Obj;
                    Float value3 = Float.parseFloat(value3Obj.toString());
                    Float value4 = Float.parseFloat(value4Obj.toString());
                    return value3.compareTo(value4);
                }

            }
        });
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Map<String, Object>> sortedList = orderListByTwoColumn(buildList(), "asc", "stock", "skcId");
        sortedList.forEach(System.out::println);
    }

    private static List<Map<String, Object>> buildList() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/Tony/Documents/workspaces/workspace/laboratory-maven/src/main/java/com/liuboyu/sort/data.txt"))));
        String collect = bufferedReader.lines().collect(Collectors.joining(""));
        return JSON.parseArray(collect, Map.class).stream().map(map -> {
            Map<String, Object> hashMap = new HashMap<>();
            map.forEach((o, o2) -> hashMap.put(o.toString(), o2));
            return hashMap;
        }).collect(Collectors.toList());
    }

}
