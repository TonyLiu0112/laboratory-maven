package com.liuboyu.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedisCheck {
	
	public static void main(String[] args) throws Exception {
		
		String inFile = "/Users/liuboyu/Desktop/in.txt";
		String newFile = "/Users/liuboyu/Desktop/new.txt";
		
		
		File in = new File(inFile);
		File news = new File(newFile);
		
		BufferedReader readerIn = new BufferedReader(new FileReader(in));
		BufferedReader readerNews = new BufferedReader(new FileReader(news));

		List<String> inList = readFile(readerIn);
		List<String> newsList = readFile(readerNews);
		
		
		inList.removeAll(newsList);
		
		for (String mac : inList) {
			System.out.println(mac);
		}
		// ce82cff65787
		
	}
	
	public static List<String> readFile(BufferedReader reader) throws Exception {
		List<String> macList = new ArrayList<String>();
		String lineStr = "";
		while ((lineStr = reader.readLine()) != null) {
			
			macList.add(lineStr);
			
        }
		
		return macList;
		
	}

}
