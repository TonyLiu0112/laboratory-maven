package com.liuboyu.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Test2 {

    public static void main(String[] args) throws IOException {
        String longStr = "asdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadfasdfasdfsadf";
        String compress = compress(longStr);
        System.out.println(compress + " " + compress.getBytes().length);
        System.out.println(uncompress(compress));
    }

    // 压缩
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    // 解压缩
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }

}
