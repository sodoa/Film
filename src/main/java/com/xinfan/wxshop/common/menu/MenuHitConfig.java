package com.xinfan.wxshop.common.menu;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author cyp
 */
public class MenuHitConfig {

	private static final Logger logger = LoggerFactory.getLogger(MenuHitConfig.class);

	public static final Object lock = new Object();

	private static Map<String, String> events = new HashMap<String, String>();

	private static MenuHitConfig instance = null;

	private MenuHitConfig() {

	}

	public static MenuHitConfig getInstatnce() {
		if (instance == null) {
			synchronized (lock) {
				instance = new MenuHitConfig();
				instance.load();
			}
		}
		return instance;
	}

	private void load() {
		try {
			PathMatchingResourcePatternResolver loader = new PathMatchingResourcePatternResolver();

			parse(loader.getResource("/config/menu_hit.xml").getInputStream());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getHitMenu(String url){
		return events.get(url);
	}

	private void parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {

		// 得到DOM解析器的工厂实例
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// 从DOM工厂中获得DOM解析器
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		// 把要解析的xml文档读入DOM解析器
		InputStream resource = is;
		// 文档对象
		Document document = dbBuilder.parse(resource);

		NodeList nodeList = document.getElementsByTagName("menu");
		if (nodeList == null || nodeList.getLength() == 0) {
			return;
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element e = (Element) nodeList.item(i);
			String id = e.getAttribute("id");

			NodeList list = e.getChildNodes();
			for (int j = 0; j < list.getLength(); j++) {
				Node urlNode = list.item(j);
				if (urlNode.getNodeType() == Node.ELEMENT_NODE) {
					String value = urlNode.getAttributes().getNamedItem("value").getNodeValue();
					events.put(value.trim(), id);
				}
			}
		}
	}

}
