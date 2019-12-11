package com.liuboyu.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImgFileCopy {

    public static void main(String[] args) throws IOException {
        File f = new File("/Users/Tony/Downloads/img/clothes.jpg");
        for (int i = 0; i < 10000; i++) {
            FileUtils.copyFile(f, new File("/Users/Tony/Downloads/img/" + UUID.randomUUID() + ".jpg"));
        }
    }

}
