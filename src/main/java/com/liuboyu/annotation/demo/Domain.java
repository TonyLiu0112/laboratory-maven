package com.liuboyu.annotation.demo;

import java.lang.reflect.Method;

public class Domain {

	public static void main(String[] args) throws Exception, IllegalAccessException, ClassNotFoundException {
		Object c = Class.forName("com.liuboyu.annotation.demo.Student").newInstance();
		Method[] methodArray = c.getClass().getDeclaredMethods();
		for (int i = 0; i < methodArray.length; i++) {
			if (methodArray[i].isAnnotationPresent(ValueBind.class)) {
				ValueBind annotation = methodArray[i].getAnnotation(ValueBind.class);
				String type = String.valueOf(annotation.type());
				String value = annotation.value()[0];
				if (type.toString().equals("INT")) {
					methodArray[i].invoke(c,new Integer[] { new Integer(value) });
				} else {
					methodArray[i].invoke(c, new String[] { value });
				}
			}
		}
		Student annotaedStudent = (Student) c;
		System.out.println(annotaedStudent.getId());
		System.out.println(annotaedStudent.getName());
	}
}
