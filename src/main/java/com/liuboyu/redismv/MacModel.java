package com.liuboyu.redismv;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MacModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1854974058527164143L;
	private String mac;
	private Set<String> hisSet;
	
	public MacModel() {
		hisSet = new HashSet<String>();
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Set<String> getHisSet() {
		return hisSet;
	}
	public void setHisSet(Set<String> hisSet) {
		this.hisSet = hisSet;
	}
	
}
