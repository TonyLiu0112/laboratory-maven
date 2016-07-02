package com.liuboyu.designmodel.java8.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Tony on 4/13/16.
 */
public class ZipCompress implements CompressionStrategy {

    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new ZipOutputStream(data);
    }
}
