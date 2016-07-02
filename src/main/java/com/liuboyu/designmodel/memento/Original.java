package com.liuboyu.designmodel.memento;

/**
 * 备忘录模式：原始类
 * @author Bob
 * @Feb 26, 2015
 */
public class Original {
	
	/**
	 * 需备份的属性
	 */
	private String value;
	
	public Original(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Memento createMemento() {
		return new Memento(value);
	}
	
	public void restoreMemento(Memento memento) {
		this.value = memento.getValue();
	}
	
}
