package com.liuboyu.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy<O,T> implements InvocationHandler {
	private O interceptor;
	private T biz;
	
	public Object bind(O o, T t) {
		this.interceptor = o;
		this.biz = t;
		return Proxy.newProxyInstance(biz.getClass().getClassLoader(), biz.getClass().getInterfaces(), this);
	}

	@SuppressWarnings("rawtypes")
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		Class clazz = proxy.getClass();
		System.out.println(clazz.getName());
		Method before = interceptor.getClass().getDeclaredMethod("before", new Class[]{String.class});
		Method after = interceptor.getClass().getDeclaredMethod("after", new Class[]{String.class});
		before.invoke(interceptor, new Object[]{"方法调用之前调用了！"});
		result = method.invoke(biz, args);
		after.invoke(interceptor, new Object[]{"方法调用之后调用了！"});
		return result;
	}

}
