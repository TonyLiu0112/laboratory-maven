package com.liuboyu.mockhttp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class NiwodaiTest {
	
	public static void main(String[] args) throws Exception {
		CountDownLatch latch = new CountDownLatch(1);
		for (int i = 0; i < 1000; i ++) {
			new Thread(new Request(latch)).start();
		}
		
		Thread.sleep(10000L);
		latch.countDown();
	}
}

/**
 * 请求
 * 
 * @author Tony
 * @date   Oct 29, 2015
 */
class Request implements Runnable {
	
	private final CountDownLatch latch;
	
	public Request(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		HttpClientUtil httpClient = new HttpClientUtil();
		String url = "http://localhost:8080/kratos-web/niwodai/coupons/exchange";
		@SuppressWarnings("serial")
		Map<String, Object> paramMap = new HashMap<String, Object>(){{
			put("phone", "18721790735");
			put("coupon_id", "14");
			put("exchange_num", "2");
		}};
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		httpClient.post(url, paramMap);
	}
	
}
