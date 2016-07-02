package com.liuboyu.redismv;

public class Import {
	
	private static final String TARGET_HOSTS = "10.161.222.14:6379,10.161.222.14:6380,10.161.172.203:6379,10.161.172.203:6380,10.161.227.158:6379,10.161.227.158:6380";
//	private static final String TARGET_HOSTS = "localhost:6379,localhost:6380,localhost:6381,localhost:6382,localhost:6383,localhost:6384";
	private static final int TIME_OUT = 2000; 
	private static final int MAX_REDIRECTIONS = 5;
	
	private static final String KEY = "_OLD";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//		Initialization init = new Initialization(TARGET_HOSTS, TIME_OUT, MAX_REDIRECTIONS);
//		init.init();
//
//		File file = new File(args[0]);
//		try {
//			ObjectInput input = new ObjectInputStream(new FileInputStream(file));
//			List<MacModel> list = (List<MacModel>) input.readObject();
//
//			for (MacModel macModel : list) {
//				System.out.println("正在添加 " + macModel.getMac());
//				for (String smac : macModel.getHisSet()) {
//					RedisClient.sadd(macModel.getMac()+KEY, smac);
//				}
//			}
//			input.close();
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}

}
