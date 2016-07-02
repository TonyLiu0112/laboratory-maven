package com.liuboyu.annotation.simple;

/**
 * @author liuboyu
 *
 */
@Myannotation1(value = "Bob")
public class Domain {
	
	@Myannotation2(desciption="good job", isAnnotation=false)
	private void fuckAnnotation() {
		System.out.println("say fuck!!");
	}
	
}
