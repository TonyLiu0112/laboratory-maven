package com.liuboyu.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

@SuppressWarnings("deprecation")
public class HbaseTest {

	public static Configuration config;

	static {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.zookeeper.quorum", "127.0.0.1");
	}

	/**
	 * 新增表
	 * 
	 * @param tableName
	 */
	public static void createTable(String tableName) {
		HBaseAdmin hbaseAdmin = null;
		try {
			hbaseAdmin = new HBaseAdmin(config);

			if (hbaseAdmin.tableExists(tableName)) {
				System.out
						.println("HbaseTest - createTable(tableName) - table is exists,do delete");
				hbaseAdmin.disableTable(tableName);
				hbaseAdmin.deleteTable(tableName);
				System.out
						.println("HbaseTest - createTable(tableName) - table deleted");
			}

			System.out.println("HbaseTest - createTable(tableName) - table is not exists,do create");
			HTableDescriptor htable = new HTableDescriptor(tableName);
			htable.addFamily(new HColumnDescriptor("c1"));
			htable.addFamily(new HColumnDescriptor("c2"));
			htable.addFamily(new HColumnDescriptor("c3"));
			hbaseAdmin.createTable(htable);
			System.out
					.println("HbaseTest - createTable(tableName) - table is not exists,create success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (hbaseAdmin != null)
					hbaseAdmin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("HbaseTest - createTable(tableName) - end create table");

	}

	/**
	 * 删除表
	 * @param tableName
	 * @throws MasterNotRunningException
	 * @throws ZooKeeperConnectionException
	 * @throws IOException
	 */
	public void dropTable(String tableName) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		HBaseAdmin hbaseAdmin = new HBaseAdmin(config);
		hbaseAdmin.disableTable(tableName);
		hbaseAdmin.deleteTable(tableName);
		hbaseAdmin.close();
	}
	
	/**
	 * 添加数据
	 * @param tableName
	 * @throws IOException
	 */
	public void doInsert(String tableName) throws IOException {
        System.out.println("start to insert data >>>>>>>>>>>>>>>>>>>>");
        
        HTablePool pool = new HTablePool(config, 1000);
        // 一个put对象代表一个row，如果需要添加第二行的数据，则需要重新实例化一个Put对象；构造的参数即为row key
        Put put = new Put("1".getBytes());
        // add方法有3个参数，第一个参数代表列族名字，第二个代表自定义类型，第三个字段代表值
        put.add("c1".getBytes(), "a".getBytes(), "I am Bobo c1:a".getBytes());
//        put.add("c1".getBytes(), "1".getBytes(), "I am Bobo c1:b".getBytes());
//        put.add("c2".getBytes(), "b".getBytes(), "I am bobo c2:b".getBytes());
//        put.add("c3".getBytes(), "c".getBytes(), "I am bobo c3:c".getBytes());
        pool.getTable(tableName).put(put);
        pool.close();
        
        System.out.println("end to insert data >>>>>>>>>>>>>>>>>>>>>");
    }
	
	/**
	 * 删除数据
	 * @param tableName
	 * @param rowKey
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void deleteRecord(String tableName, String rowKey) throws IOException {
        HTable htable = new HTable(config, tableName);
        @SuppressWarnings("rawtypes")
		List list = new ArrayList();
        Delete delete = new Delete(rowKey.getBytes());
        delete.deleteColumn("c1".getBytes(), "a".getBytes());
        list.add(delete);
        htable.delete(list);
        htable.close();
    }
	
	/**
	 * 查询所有
	 * @param tableName
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void queryAll(String tableName) throws IOException {
        HTablePool pool = new HTablePool(config, 1000);
        ResultScanner scanner = pool.getTable(tableName).getScanner(new Scan());
       
        for (Result result : scanner) {
            for (KeyValue keyValue : result.raw()) {
                System.out.println("ROW:["+new String(result.getRow())+"] "
                                 + "COLUMN+CELL[column="+new String(keyValue.getFamily())+":"+new String(keyValue.getQualifier())+", "
                                              + "timespan="+ keyValue.getTimestamp() +", value="+new String(keyValue.getValue())+"]");
            }
        }
       
    }
	
	/**
	 * 根据列族查询
	 * @param tableName
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void queryByColumnFamily(String tableName) throws IOException {
	       
        // 设置查询条件
        byte[] family = Bytes.toBytes("c1");
        byte[] qualifier = Bytes.toBytes("b");
        byte[] value = Bytes.toBytes("I am bobo c1:b");
       
        HTable table = new HTable(config, tableName);
       
        @SuppressWarnings("rawtypes")
		List filterList = new ArrayList();
       
        // 查询条件1
        Filter filter = new SingleColumnValueFilter(family, qualifier, CompareOp.EQUAL, value);
        filterList.add(filter);
       
        FilterList flist = new FilterList(filter);
       
        Scan scan = new Scan();
        // 设置 family 和 qualifier，此处一定要设置，否则会出现匪夷所思的查询结果
        scan.addColumn(family, qualifier);
        scan.setFilter(flist);
       
        ResultScanner scanner = table.getScanner(scan);
       
        for (Result result : scanner) {
            for (KeyValue keyValue : result.raw()) {
                System.out.println("ROW:["+new String(result.getRow())+"] "
                                 + "COLUMN+CELL[column="+new String(keyValue.getFamily())+":"+new String(keyValue.getQualifier())+", "
                                              + "timespan="+ keyValue.getTimestamp() +", value="+new String(keyValue.getValue())+"]");
            }
        }
       
        table.close();
    }
	
	public static void main(String[] args) throws Exception {
		HbaseTest hbase = new HbaseTest();
//		hbase.createTable("luo_yu_feng");
		hbase.doInsert("luo_yu_feng");
	}

}
