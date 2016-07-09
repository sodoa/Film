package com.xinfan.wxshop.common.config;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

/**
 * 
 * 文件配置管理
 * 
 * @author cyp
 *
 */
public class FileConfig {
	
	public static FileConfig instaince;
	
	private Properties prop;
	
	public static FileConfig getInstance() {
		try {
			if (instaince == null) {
				instaince = new FileConfig();
				instaince.prop = new Properties();
				ClassPathResource loader = new ClassPathResource(
						"/config/application.properties");
				instaince.prop.load(loader.getInputStream());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return instaince;
	}
	
	public String getString(String name){
		return prop.getProperty(name);
	}
	
	public String getString(String name, String defaultValue) {
		return prop.getProperty(name, defaultValue);
	}
	
	public int getInt(String name) {
		return Integer.parseInt(getString(name));
	}
	
	public static void main(String[] args){
		String configId = FileConfig.getInstance().getString("configId");
		System.out.println(configId);
	}

	public Properties getProp() {
		return prop;
	}
}
