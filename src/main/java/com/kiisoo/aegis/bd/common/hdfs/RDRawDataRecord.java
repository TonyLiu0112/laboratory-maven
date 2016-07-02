package com.kiisoo.aegis.bd.common.hdfs;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Writable;

public class RDRawDataRecord implements Writable {

	private String smac;

	private String dmac;

	private int hrssi;

	private int lrssi;

	private long fstamp;

	private long lstamp;

	private long maxstamp;

	private long minstamp;

	private long stamp;

	public RDRawDataRecord() {
	}

	public void readFields(DataInput in) throws IOException {
		this.smac = in.readUTF();
		this.dmac = in.readUTF();
		this.hrssi = in.readInt();
		this.lrssi = in.readInt();
		this.fstamp = in.readLong();
		this.lstamp = in.readLong();
		this.maxstamp = in.readLong();
		this.minstamp = in.readLong();
		this.stamp = in.readLong();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(StringUtils.isNotBlank(this.smac) ? this.smac : "");
		out.writeUTF(StringUtils.isNotBlank(this.dmac) ? this.dmac : "");
		out.writeInt(this.hrssi);
		out.writeInt(this.lrssi);
		out.writeLong(this.fstamp);
		out.writeLong(this.lstamp);
		out.writeLong(this.maxstamp);
		out.writeLong(this.minstamp);
		out.writeLong(this.stamp);
	}

	public String getSmac() {
		return smac;
	}

	public void setSmac(String smac) {
		this.smac = smac;
	}

	public String getDmac() {
		return dmac;
	}

	public void setDmac(String dmac) {
		this.dmac = dmac;
	}

	public int getHrssi() {
		return hrssi;
	}

	public void setHrssi(int hrssi) {
		this.hrssi = hrssi;
	}

	public int getLrssi() {
		return lrssi;
	}

	public void setLrssi(int lrssi) {
		this.lrssi = lrssi;
	}

	public long getFstamp() {
		return fstamp;
	}

	public void setFstamp(long fstamp) {
		this.fstamp = fstamp;
	}

	public long getLstamp() {
		return lstamp;
	}

	public void setLstamp(long lstamp) {
		this.lstamp = lstamp;
	}

	public long getMaxstamp() {
		return maxstamp;
	}

	public void setMaxstamp(long maxstamp) {
		this.maxstamp = maxstamp;
	}

	public long getMinstamp() {
		return minstamp;
	}

	public void setMinstamp(long minstamp) {
		this.minstamp = minstamp;
	}

	public long getStamp() {
		return stamp;
	}

	public void setStamp(long stamp) {
		this.stamp = stamp;
	}

}
