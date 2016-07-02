package com.liuboyu.hdfsinput;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;
import com.kiisoo.aegis.bd.common.hdfs.RDRawDataRecord;
import com.liuboyu.jdbc.mysql.MysqlDriver;

public class HdfsInput {
	
	static String sql = "insert into t_hdfs_record_YanAn (id, smac, dmac, hrssi, lrssi, fstamp, lstamp, maxstamp, minstamp, stamp) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static void main(String[] args) throws Exception {
		Connection conn = MysqlDriver.getConnection();
		File[] files = new File("/tmp/data-YanAn/").listFiles();
		for (File file : files) {
			List<String> lines = FileUtils.readLines(file);
			for (String json : lines) {
				PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
				RDRawDataRecord record = JSONObject.parseObject(json, RDRawDataRecord.class);
				stat.setString(1, record.getSmac());
				stat.setString(2, record.getDmac());
				stat.setInt(3, record.getHrssi());
				stat.setInt(4, record.getLrssi());
				stat.setTimestamp(5, new Timestamp(record.getFstamp()));
				stat.setTimestamp(6, new Timestamp(record.getLstamp()));
				stat.setTimestamp(7, new Timestamp(record.getMaxstamp()));
				stat.setTimestamp(8, new Timestamp(record.getMinstamp()));
				stat.setTimestamp(9, new Timestamp(record.getStamp()));
				stat.executeUpdate();
				stat.close();
			}
		}
		
	}

}
