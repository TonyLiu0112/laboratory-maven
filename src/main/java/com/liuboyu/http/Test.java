package com.liuboyu.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Test {

//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(() -> {
//                while (true) {
//                    try {
//                        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//                        HttpGet httpGet = new HttpGet("https://api.maclookup.app/v1/macs/da:a1:19:ab:4c:65");
//                        CloseableHttpResponse execute = httpClient.execute(httpGet);
//                        log.info(execute.getEntity().toString());
//                        Thread.sleep(3000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        Thread.sleep(Integer.MAX_VALUE);
//    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet httpGet = new HttpGet("http://api.maclookup.app/v1/macs/da:a1:19:ab:4c:65");
        HttpGet httpGet = new HttpGet("http://api.maclookup.app/v1/macs/abcfe0");
        long s = System.currentTimeMillis();
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        log.info("time: {}", System.currentTimeMillis() - s);
        log.info(EntityUtils.toString(execute.getEntity()));
    }

}
