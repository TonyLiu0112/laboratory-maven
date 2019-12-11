package com.liuboyu.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 生成mysql导入数据文件
 * 仅用于测试load data infile
 * <p>
 * example:
 * <code>
 * 1,Tony,29,3001,1
 * 2,Bob,28,1309,1
 * 3,Pig,24,777,1
 * 4,Kim,26,2000,1
 * </code>
 *
 * @author Tony
 */
public class MySqlDataFileGenerator {

    private static final String VERSION = "1";

    private static final Random FANS = new Random();

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/Tony/Downloads/data.txt");
        ArrayList<String> lineBuffer = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            lineBuffer.add(String.format("%s,%s,%s,%s,%s\n", i, "Tony" + i, 29, FANS.nextInt(100000), VERSION));
            if (lineBuffer.size() % 10000 == 0 || i == 1000000) {
                FileUtils.writeLines(file, lineBuffer, "", true);
                lineBuffer.clear();
            }
        }
    }

}
