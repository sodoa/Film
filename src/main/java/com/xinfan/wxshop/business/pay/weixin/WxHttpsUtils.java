package com.xinfan.wxshop.business.pay.weixin;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class WxHttpsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(WxHttpsUtils.class);

	// 连接超时时间，默认10秒
	private static int socketTimeout = 10000;

	// 传输超时时间，默认30秒
	private static int connectTimeout = 30000;

	// 请求器的配置
	private static RequestConfig requestConfig;

	// HTTP请求器
	private static CloseableHttpClient httpclient;
	
	private static String KEY_VERSIONS = "1319785801";
	
	private static String KEY_PATH = "/keys/apiclient_cert.p12"	;

	static {

		KeyStore keyStore = null;
		FileInputStream instream = null;// 加载本地的证书进行https加密传输
		try {
			keyStore = KeyStore.getInstance("PKCS12");
			
			System.out.println("*****************"+KEY_VERSIONS);
			
			ClassPathResource loader = new ClassPathResource("/config/apiclient_cert.p12");
			
			instream = new FileInputStream(loader.getFile());// 加载本地的证书进行https加密传输
			
			System.out.println("*****************"+instream);
			
			keyStore.load(instream, KEY_VERSIONS.toCharArray());// 设置证书密码
			
			

			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, KEY_VERSIONS.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
					.build();

			// 根据默认超时限制初始化requestConfig
			requestConfig = RequestConfig.custom()
					.setSocketTimeout(socketTimeout)
					.setConnectTimeout(connectTimeout).build();

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
	}

	public static Map<String, String> SSLPostXmlWithResult(String url, String xmlParam) {
		
		if(logger.isDebugEnabled()){
			logger.debug("xml是:" + xmlParam);
		}
		
		HttpPost httpost = new HttpPost(url);
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "UTF-8");
			
			if(logger.isDebugEnabled()){
				logger.debug("json是:" + jsonStr);
			}

			if (jsonStr.indexOf("FAIL") != -1) {
				return null;
			}
			Map map = doXMLParse(jsonStr);
			return map;

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		return null;
	}

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static SortedMap doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		SortedMap m = new TreeMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

}