package com.liuboyu.annotation.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.liuboyu.annotation.simple.Myannotation1;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Myannotation1
public @interface ValueBind {
	
	enum FieldType {
		STRING, INT
	}
	
	FieldType type() default FieldType.STRING;
	
	String[] value() default {};
	
}
