package com.liuboyu.designmodel.prototype;

/**
 * 原型模式（深拷贝和浅拷贝）
 * @author Bob
 * @Feb 24, 2015
 */
public class Source implements Cloneable {
	
	private Integer age;
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getAge() {
		return age;
	}
	
	/* (non-Javadoc)
	 * @see Object#clone()
	 * 仅仅是浅拷贝
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * 深拷贝
	 * @return
	 */
	public Object deepClone() {
		Source s = new Source();
		s.setAge(getAge());
		return s;
	}
	
}
