package com.liuboyu.mockhttp;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * mock http request
 * @author liuboyu
 *
 */
public class MockHttpReq {
	
	/**
	 * http get request
	 * @param url
	 * @param query
	 * @param charset
	 * @param pertty
	 * @return
	 */
	public String doGet(String url, String query, String charset, boolean pertty) {
		StringBuffer response = new StringBuffer();
		HttpClient httpClient = new HttpClient();
		HttpMethod httpMethod = new GetMethod(url);
		
		if (query != null && !"".equals(query)) 
			httpMethod.setQueryString(query);
		
		try {
			httpClient.executeMethod(httpMethod);
			
			if (httpMethod.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream(), charset));
				String line;
				while((line = reader.readLine()) != null) {
					if (pertty)
						response.append(line).append(File.separator);
					else 
						response.append(line);
				}
				reader.close();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
	
	/**
	 * http post request
	 * @param url
	 * @param reqParams
	 * @param charset
	 * @param pertty
	 * @return
	 */
	public String doPost(String url, Map<String, Object> reqParams, String charset, boolean pertty) {
		StringBuffer response = new StringBuffer();
		HttpClient httpClient = new HttpClient();
		HttpMethod httpMethod = new PostMethod(url);
		
		if (!reqParams.isEmpty()) {
			HttpMethodParams params = new HttpMethodParams();
			for (Entry<String, Object> entry : reqParams.entrySet()) {
				params.setParameter(entry.getKey(), entry.getValue());
			}
			httpMethod.setParams(params);
		}
		
		try {
			httpClient.executeMethod(httpMethod);
			if (httpMethod.getStatusCode() == HttpStatus.SC_OK) {
				System.out.println(new String(httpMethod.getResponseBody()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpMethod.getResponseBodyAsStream(), charset));
				String line;
				while((line = reader.readLine()) != null) {
					if (pertty)
						response.append(line).append(File.separator);
					else 
						response.append(line);
				}
				reader.close();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
	
	public static void main(String[] args) {
		
		String url = "http://localhost:8080/kratos-web/niwodai/test";
		Map<String, Object> paramMap = new HashMap<String, Object>(){{
			put("name", "liuboyu");
		}};
		
		new MockHttpReq().doPost(url, paramMap, "UTF-8", false);
		
	}
	
}
