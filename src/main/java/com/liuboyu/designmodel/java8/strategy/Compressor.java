package com.liuboyu.designmodel.java8.strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Tony on 4/13/16.
 */
public class Compressor {

    private final CompressionStrategy compressionStrategy;

    public Compressor(CompressionStrategy compressionStrategy) {
        this.compressionStrategy = compressionStrategy;
    }

    public void compress(Path inFile, File outFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            Files.copy(inFile, compressionStrategy.compress(outputStream));
        }
    }
}
