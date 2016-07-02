package com.liuboyu.mockhttp;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HttpClientUtil {
	public void post(String url, Map<String, Object> paramte) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (paramte != null && !paramte.isEmpty()) {
			for (Entry<String, Object> entry : paramte.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(entity, "UTF-8"));
					System.out.println(jsonObj.toString());
					if (jsonObj.get("code").equals("0000")) {
						System.out.println(Thread.currentThread().getName() + " " + jsonObj.get("isVip"));
					}
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		validate(time);
		sendcode(time);
	}

	/**
	 * 校验接口
	 */
	@SuppressWarnings("serial")
	public static void validate(final long time) {
		// Demo
		HttpClientUtil client = new HttpClientUtil();

		// 请求参数
		Map<String, Object> req = new TreeMap<String, Object>() {
			{
				put("action", "validate");
				put("phone", "18721790735");
				put("partnerId", "384");
				put("time", time);
				put("signType", "1");
			}
		};

		// 生成MD5
		String md5 = "";
		for (Entry<String, Object> entry : req.entrySet())
			md5 += String.format("%s=%s&", entry.getKey(), entry.getValue());
		md5 += "key=111d0eab6830c0a9c37fa61f25f212be";

		System.out.println("> > > > > " + md5);

		req.put("sign", DigestUtils.md5Hex(md5));

		// http req
		client.post("http://openapi2.niwodai.org/external-war/external/fastRegister.do", req);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public static void sendcode(final long time) {
		// Demo
				HttpClientUtil client = new HttpClientUtil();

				// 请求参数
				Map<String, Object> req = new TreeMap<String, Object>() {
					{
						put("action", "sendcode");
						put("phone", "18721790735");
						put("partnerId", "384");
						put("time", time);
						put("signType", "1");
					}
				};

				// 生成MD5
				String md5 = "";
				for (Entry<String, Object> entry : req.entrySet())
					md5 += String.format("%s=%s&", entry.getKey(), entry.getValue());
				md5 += "key=111d0eab6830c0a9c37fa61f25f212be";

				System.out.println("> > > > > " + md5);

				req.put("sign", DigestUtils.md5Hex(md5));

				// http req
				client.post("http://openapi2.niwodai.org/external-war/external/fastRegister.do", req);
	}
}
