package com.liuboyu.flume;

import java.io.File;
import java.io.FileOutputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		File file = new File("/Users/liuboyu/Documents/tools/flume/flume-dir/temp/hello.txt");
		@SuppressWarnings("unused")
		int i = 0;
//		for (;;) {
//			i ++;
//			BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file));
//			outStream.write(("刘博宇"+i).getBytes());
//			outStream.flush();
//			outStream.close();
//			Thread.sleep(2000);
//		}
		for (;;) {
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write("liuboyu".getBytes());
			outputStream.flush();
			outputStream.close();
			Thread.sleep(2000);
		}
	}
	
}
