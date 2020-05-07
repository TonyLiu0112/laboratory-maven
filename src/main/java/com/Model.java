package com;

import org.apache.commons.lang3.StringUtils;

public class Model {

    public static String convert(String text) {
        if (StringUtils.isBlank(text))
            return text;
        StringBuilder builder = new StringBuilder();
        if (text.contains("_")) {
            String[] worlds = text.split("_");
            if (worlds.length == 1) {
                return worlds[0];
            }
            for (String world : worlds) {
                if (StringUtils.isBlank(world))
                    continue;
                builder.append(world.substring(0, 1).toUpperCase()).append(world.substring(1));
            }
        } else {
            String[] letters = text.split("");
            for (String letter : letters) {
                if (StringUtils.isAllUpperCase(letter)) {
                    builder.append("_");
                }
                builder.append(letter);
            }
            if (StringUtils.equals(builder.substring(0, 1), "_")) {
                builder.deleteCharAt(0);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String text1 = "WhatIsYouName";
        String text2 = "WhatIs_You_Name";
        String text3 = "What_Is_You_Name";
        String text4 = "What_";
        String text5 = "_What_";
        String text6 = "_What";
        String text7 = "";
        String text8 = "      ";

        System.out.println(convert(text1));
        System.out.println(convert(text2));
        System.out.println(convert(text3));
        System.out.println(convert(text4));
        System.out.println(convert(text5));
        System.out.println(convert(text6));
        System.out.println(convert(text7));
        System.out.println(convert(text8));
    }

}
