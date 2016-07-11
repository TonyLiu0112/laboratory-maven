package com.liuboyu.kafka;

/**
 * 基础元数据,描述一个设备rssi上某一个坐标的所有属性信息
 * <p>
 * Created by Tony on 4/27/16.
 */
public class Metadata {

    /**
     * 数据发送的设备mac标识
     */
    private String mac;

    /**
     * 当前数据收集到的手机mac
     */
    private String sMac;

    /**
     * 当前数据的最大rssi
     */
    private int maxRssi;

    /**
     * 当前数据最小的rssi
     */
    private int minRssi;

    private long maxTimestamp;

    private long minTimestamp;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getsMac() {
        return sMac;
    }

    public void setsMac(String sMac) {
        this.sMac = sMac;
    }

    public int getMaxRssi() {
        return maxRssi;
    }

    public void setMaxRssi(int maxRssi) {
        this.maxRssi = maxRssi;
    }

    public int getMinRssi() {
        return minRssi;
    }

    public void setMinRssi(int minRssi) {
        this.minRssi = minRssi;
    }

    public long getMinTimestamp() {
        return minTimestamp;
    }

    public void setMinTimestamp(long minTimestamp) {
        this.minTimestamp = minTimestamp;
    }

    public long getMaxTimestamp() {
        return maxTimestamp;
    }

    public void setMaxTimestamp(long maxTimestamp) {
        this.maxTimestamp = maxTimestamp;
    }

    @Override
    public String toString() {
        return "<mac:" + mac + " sMac:" + sMac + " maxRssi:" + maxRssi + " minRssi:" + minRssi
                + " maxTimestamp:" + maxTimestamp + " minTimestamp:" + minTimestamp + ">";
    }

}
