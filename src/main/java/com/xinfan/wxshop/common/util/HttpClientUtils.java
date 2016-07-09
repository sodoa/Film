package com.xinfan.wxshop.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinfan.wxshop.common.config.FileConfig;

public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	private static CloseableHttpClient client = null;

	static {

		String max = FileConfig.getInstance().getString("httpclient.maxthread", "10");

		int intMax = Integer.parseInt(max);
		cm.setMaxTotal(intMax);// 连接池最大并发连接数
		cm.setDefaultMaxPerRoute(intMax);// 单路由最大并发数
		client = HttpClients.custom().setConnectionManager(cm).build();
	}

	private HttpClientUtils() {
	}

	/*
	 * 数据发送方式可以采用其它的方式，如httpclient等。
	 */
	public static HttpResponse post(String url, Map<String, Object> paramMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("url :" + url);
			logger.debug("map :" + JSONUtils.toJSONString(paramMap));
		}

		try {
			HttpPost post = new HttpPost(url);

			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(mapToPostParam(paramMap), "UTF-8");
			// post.addHeader("Content-type",
			// "application/x-www-form-urlencoded");
			post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			post.setEntity(formEntiry);
			HttpResponse response = client.execute(post);

			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	public static String postWithResultString(String url, Map<String, Object> paramMap) {

		if (logger.isDebugEnabled()) {
			logger.debug("url :" + url);
			logger.debug("map :" + JSONUtils.toJSONString(paramMap));
		}

		try {
			HttpPost post = new HttpPost(url);

			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(mapToPostParam(paramMap), "UTF-8");
			// post.addHeader("Content-type",
			// "application/x-www-form-urlencoded");
			post.setHeader("Content-Type", "text/html;charset=UTF-8");
			post.setEntity(formEntiry);
			HttpResponse response = client.execute(post);

			return streamToString(response.getEntity().getContent());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String postWithString(String url, String xml) {

		if (logger.isDebugEnabled()) {
			logger.debug("url :" + url);
			logger.debug("map :" + xml);
		}

		try {
			HttpPost post = new HttpPost(url);

			// 创建UrlEncodedFormEntity对象
			StringEntity entity = new StringEntity(xml, "UTF-8");
			// post.addHeader("Content-type",
			// "application/x-www-form-urlencoded");
			post.setHeader("Content-Type", "text/html;charset=UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);

			return streamToString(response.getEntity().getContent());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static List<NameValuePair> mapToPostParam(Map<String, Object> paramMap) {

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object values = paramMap.get(key);
			if (values instanceof String[]) {
				for (String v : (String[]) values) {
					parameters.add(new BasicNameValuePair(key, v));
				}
			} else {
				String value = String.valueOf(values);
				parameters.add(new BasicNameValuePair(key, value));
			}
		}

		return parameters;
	}

	public static String streamToString(InputStream is) {

		StringBuilder responseXml = new StringBuilder();
		String line = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		try {
			for (line = br.readLine(); line != null; line = br.readLine()) {
				System.out.println(line);
				responseXml.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseXml.toString();
	}

}
