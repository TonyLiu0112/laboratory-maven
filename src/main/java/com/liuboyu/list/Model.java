package com.liuboyu.list;

public class Model {
	
	private long id;
	private long count;
	private String name;
	
	public Model(long id, long count, String name) {
		this.id = id;
		this.name = name;
		this.count = count;
	}
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
