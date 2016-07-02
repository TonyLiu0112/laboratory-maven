package com.liuboyu.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class Test {
	public static void main(String[] args) throws Exception {
		
		File file = new File("/Users/liuboyu/Downloads/base64.txt");
		InputStream input = new FileInputStream(file);
		byte[] bytes = new byte[10000];
		input.read(bytes);
		input.close();
		byte[] result = new Base64().decode(bytes);
		System.out.println(new String(result));
	}
}
