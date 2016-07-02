package com.liuboyu.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
	
	private Object target;

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("[代理]调用方法之前......");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("[代理]调用方法之后......");
		return result;
	}
	
	/**
	 * 创建代理
	 * @param target
	 * @return
	 */
	public Object newProxy(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

}
