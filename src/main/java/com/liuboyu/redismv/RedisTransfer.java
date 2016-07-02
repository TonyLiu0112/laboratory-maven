package com.liuboyu.redismv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RedisTransfer {
	
	private static final String SOURCE_HOSTS = "192.168.0.241:6379,192.168.0.242:6379,192.168.0.243:6379,192.168.0.244:6379,192.168.0.245:6379,192.168.0.246:6379";
	
	//private static final String TARGET_HOSTS = "10.161.222.14:6379,10.161.222.14:6380,10.161.172.203:6379,10.161.172.203:6380,10.161.227.158:6379,10.161.227.158:6380";
	private static final String TARGET_HOSTS = "localhost:6379,localhost:6380,localhost:6381,localhost:6382,localhost:6383,localhost:6384";
	private static final int TIME_OUT = 2000; 
	private static final int MAX_REDIRECTIONS = 5;
	
	private static final String KEY = "_OLD";
	
	private static final List<String> macList = new ArrayList<String>(){{
		add("60c5a861b2b8");
		add("60c5a861b22e");
		add("60c5a861b223");
		add("60c5a861b170");
		add("60c5a861b0e3");
		add("60c5a861b0ba");
		add("60c5a861af47");
		add("60c5a861addd");
		add("60c5a861adcd");
		add("60c5a861adc9");
		add("60c5a861ac05");
		add("60c5a861abd3");
		add("60c5a861aaba");
		add("60c5a861aa77");
		add("60c5a861a8f5");
		add("60c5a861a8f4");
		add("60c5a861a8d6");
		add("60c5a8611003");
		add("60c5a860fde5");
		add("60c5a860fd9a");
		add("60c5a860fc39");
		add("60c5a860fbe8");
		add("60c5a860f8c2");
		add("60c5a860f884");
		add("60c5a860f87a");
		add("60c5a860f7b4");
		add("60c5a860f0d4");
		add("60c5a860ecdd");
		add("60c5a860bd41");
		add("60c5a860bc2c");
		add("60c5a860bc12");
		add("60c5a860bbde");
		add("60c5a8000033");
	}};
	
	public static void main(String[] args) {
		
		// source
//		Initialization init = new Initialization(SOURCE_HOSTS, TIME_OUT, MAX_REDIRECTIONS);
//		init.init();
//		List<MacModel> list = new ArrayList<MacModel>();
//		for (String mac : macList) {
//			MacModel macModel = new MacModel();
//			Set<String> hisMacs = RedisClient.smembers(mac + KEY);
//			if (hisMacs.isEmpty())
//				continue ;
//			macModel.setMac(mac);
//			macModel.setHisSet(hisMacs);
//			list.add(macModel);
//		}
//		init.destory();
//		wirteToFile(list);
		// target
//		JedisCluster targetCluster = new JedisCluster(new HashSet<HostAndPort>(){{
//			add(new HostAndPort("115.29.210.130", 6379));
//			add(new HostAndPort("115.29.210.130", 6380));
//			add(new HostAndPort("115.29.168.108", 6379));
//			add(new HostAndPort("115.29.168.108", 6380));
//			add(new HostAndPort("115.29.205.118", 6379));
//			add(new HostAndPort("115.29.205.118", 6380));
//		}}, TIME_OUT, MAX_REDIRECTIONS);
//		
//		System.out.println(targetCluster.get("liuboyu"));
		
//		for (Entry<String, Set<String>> entry : hisMap.entrySet()) {
//			System.out.println(entry.getKey());
//			for (String smac : entry.getValue()) {
//				targetCluster.sadd(entry.getKey()+KEY, smac);
//			}
//		}
//		targetCluster.close();
	}

	private static void wirteToFile(List<MacModel> list) {
		File file = new File("/tmp/macModel");
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(list);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
