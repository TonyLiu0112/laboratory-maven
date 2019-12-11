package com.liuboyu.http;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class MacValidCheckMain {

	public static void main(String[] args) {
		try {
			List<String> ms = FileUtils.readLines(new File("/Users/Tony/Downloads/i.txt"), "UTF8");
			Set<String> macall = new HashSet<String>();

			for (String m : ms) {
				macall.add(StringUtils.substring(m, 0, 6));
			}

			ms.clear();

			String url = "http://api.maclookup.app/v1/macs/%s";

			HttpClient client = new HttpClient();
			String res = "";
			String c = "";
			GetMethod method = null;
			String mc = null;
			int count = 0;
			List<String> vms = new ArrayList<String>();
			List<String> ivms = new ArrayList<String>();

			for (String m : macall) {
				url = "http://api.maclookup.app/v1/macs/" + m;
				method = new GetMethod(url);
				client.executeMethod(method);

				res = method.getResponseBodyAsString();

				if (StringUtils.contains(res, "mac")) {
					c = StringUtils.substringBetween(res, "company\":\"", "\",\"address");
					mc = m + " - " + c;
					vms.add(mc);
				} else if (StringUtils.contains(res, "error")) {
					ivms.add(m);
				}

				count++;

				if (count % 1000 == 0) {
					FileUtils.writeLines(new File("f/m/vm.txt"), vms, true);
					FileUtils.writeLines(new File("f/m/ivm.txt"), ivms, true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
