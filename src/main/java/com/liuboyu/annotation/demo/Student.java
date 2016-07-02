package com.liuboyu.annotation.demo;

import com.liuboyu.annotation.demo.ValueBind.FieldType;

/**
 * @author liuboyu
 *
 */
public class Student {

	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	@ValueBind(type=FieldType.INT, value="1")
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@ValueBind(type=FieldType.STRING, value="admin")
	public void setName(String name) {
		this.name = name;
	}
	
}
