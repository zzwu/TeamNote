package com.got.teamnote.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	/**
	 * 请求对应地址，并且以字符串的形式返回访问结果。
	 * @param url
	 * @param requestParams
	 * @return
	 */
	public static String post(String url, Map<String, String> requestParams) {
		List<NameValuePair> params = getNameValuePairs(requestParams);
		try {
			DefaultHttpClient  client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(post);
			if (200 == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<NameValuePair> getNameValuePairs(
			final Map<String, String> map) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			pairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		return pairs;
	}
	
}
