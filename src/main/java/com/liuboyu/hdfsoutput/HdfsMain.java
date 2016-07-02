package com.liuboyu.hdfsoutput;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.Job;

import com.alibaba.fastjson.JSONObject;
import com.kiisoo.aegis.bd.common.hdfs.RDRawDataRecord;

public class HdfsMain {
	// hdfs://192.168.0.247:38301/user/bd/raw/data/
	private static Configuration conf;
	private static String beginTime = "";
	private static String endTime = "";
	private static String HDFS_PATH = "hdfs://%s:%s/user/bd/raw/data/";
	private static String OUTPUT_PATH = "./hdfs-json-file";
	private static String MAC = "";
	private static boolean all = false;

	public static void main(String[] args) throws ParseException {
		loadPath(args);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate = sdf.parse(beginTime);
		Date endDate = sdf.parse(endTime);
		while (endDate.compareTo(beginDate) >= 0) {
			OUTPUT_PATH = sdf.format(beginDate).replace("/", "");
			String hdfsPath = HDFS_PATH + sdf.format(beginDate);
			exec(hdfsPath);
			System.out.println("finish to output >> " + hdfsPath);
			beginDate = DateUtils.addDays(beginDate, 1);
		}
	}
	
	public static void exec(String hdfsPath) {
		Job job = null;
		try {

			job = Job.getInstance();
			conf = job.getConfiguration();

			Path path = new Path(hdfsPath);
			FileSystem fs = path.getFileSystem(conf);

			// 文件夹
			if (fs.isDirectory(path)) {
				FileStatus[] fileStatusArray = fs.listStatus(path);
				for (FileStatus file : fileStatusArray) {
					readFile(file.getPath());
				}
			} else {
				readFile(path);
			}
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conf.clear();
			job = null;
		}
	}

	public static void loadPath(String args[]) {
		HDFS_PATH = String.format(HDFS_PATH, args[0], args[1]);
		MAC = args[2];
		beginTime = args[3];
		endTime = args[4];
		if (MAC.equals("all"))
			all = true;
			
	}

	public static void readFile(Path path) throws Exception {
		SequenceFile.Reader reader = new SequenceFile.Reader(conf,
				SequenceFile.Reader.file(path));
		RDRawDataRecord data = new RDRawDataRecord();
		LongWritable wkey = new LongWritable();
		boolean flag = reader.next(wkey, data);
		List<String> lines = new ArrayList<String>();
		File file = new File(OUTPUT_PATH);
		while (flag) {
			if (all) {
				lines.add(JSONObject.toJSONString(data));
			} else {
				if (data.getDmac().equals(MAC)) {
					lines.add(JSONObject.toJSONString(data));
					// parser(data);
				}
			}
			flag = reader.next(wkey, data);
		}
		FileUtils.writeLines(file, lines, true);
		reader.close();
	}

	@SuppressWarnings("unused")
	private static int instroeCount = 0;
	private static final int confRssiMax = -45;
	private static final int confRssiMiddle = -60;
	private static final int confRssiMin = -90;

	public static void parser(RDRawDataRecord data) {
		if (position(data.getHrssi(), data.getLrssi()) == 1)
			instroeCount++;
	}

	public static int position(int paraRssiMax, int paraRssiMin) {
		int point = 0;// 默认店外
		if (paraRssiMax >= confRssiMax) {
			point = 1;// 在店
		} else if (paraRssiMax >= confRssiMiddle) {
			if (paraRssiMin >= confRssiMiddle) {
				point = 1;// 在店
			} else {
				// 店外
			}
		} else if (paraRssiMax >= confRssiMin) {
			// 店外
		} else {
			point = -1;// 废弃数据
		}
		return point;
	}

}
