package com.liuboyu;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test111 {

    public static void main(String[] args) throws IOException {
        JSONObject json = new JSONObject();
        HttpEntity entity = new InputStreamEntity(new FileInputStream(new File("/tmp/aaa")));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut put = new HttpPut("http://10.173.33.30:8030/api/goods_product/goods_product_20191030023548815_2/_load?label=id,brand_id,code,name,fabrics,year,season,category_id,status,series_type,version,brand,create_time&columns=8030&column_separator=,");
        put.setHeader("Expect", "100-continue");
        put.setHeader("Authorization", "Basic " + Base64.encode("p2p@qscube:db.p2p.123".getBytes()));
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(10000).setSocketTimeout(1200000).build();
        put.setConfig(requestConfig);
        put.setEntity(entity);
        CloseableHttpResponse response = httpclient.execute(put);
        System.out.println(response);
    }


}
