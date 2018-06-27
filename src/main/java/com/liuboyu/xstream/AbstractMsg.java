package com.liuboyu.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author wuwenming
 * 
 */
@XStreamAlias("xml")
public abstract class AbstractMsg implements Msg {

	@XStreamAlias("ToUserName")
	protected String toUserName="";

	@XStreamAlias("FromUserName")
	protected String fromUserName="";

	@XStreamAlias("CreateTime")
	protected String createTime="";

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public abstract String getMsgType();

}
