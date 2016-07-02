package com.liuboyu.aop;


public class Main {
	public static void main(String[] args) throws Exception {
//		Object obj = Class.forName("demo.liuboyu.aop.Log").newInstance();
//		Class clazz = obj.getClass();
//		Method aaa = clazz.getDeclaredMethod("println", new Class[]{Object.class});
//		aaa.invoke(obj, "aaa");
		
		Worker work = (Worker) new DynamicProxy<Operation, Worker>().bind(new Log(), new MyWorker()); 
		
		work.dowork("刘博宇");
		
	}
}