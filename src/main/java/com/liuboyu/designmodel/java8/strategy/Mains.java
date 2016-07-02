package com.liuboyu.designmodel.java8.strategy;

import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Tony on 4/13/16.
 */
public class Mains {

    public static void main(String[] args) {
        Compressor gzip = new Compressor(GZIPOutputStream::new);
        Compressor zip = new Compressor(ZipOutputStream::new);

//         gzip.compress(inPath, outFile);
//         zip.compress(inPath);

    }

}
