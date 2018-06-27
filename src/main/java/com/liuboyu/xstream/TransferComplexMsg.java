package com.liuboyu.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 复合消息类型，具有所有消息所特有的字段，在request报文转消息对象时用来做消息具体类型的判断
 *
 * @author wuwenming
 */
public class TransferComplexMsg extends AbstractMsg {

    @XStreamAlias("MsgType")
    private String msgType = "";
    @XStreamAlias("Event")
    protected String event = "";

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
