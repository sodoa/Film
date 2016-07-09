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
public class ParamtersFileConfig {
	
	private static ParamtersFileConfig instance;
	
	private Properties prop;
	
	public static ParamtersFileConfig getInstance() {
		try {
			if (instance == null) {
				instance = new ParamtersFileConfig();
				instance.prop = new Properties();
				ClassPathResource loader = new ClassPathResource(
						"/config/paramters.properties");
				instance.prop.load(loader.getInputStream());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return instance;
	}
	
	public String getString(String name){
		return prop.getProperty(name);
	}
	
	public String getString(String name, String defaultValue) {
		return prop.getProperty(name, defaultValue);
	}
	
	public long getLong(String name) {
		return Long.parseLong(getString(name));
	}
	
	public static void main(String[] args){
		String configId = ParamtersFileConfig.getInstance().getString("yunpian_sms_apikey");
		System.out.println(configId);
	}

	public Properties getProp() {
		return prop;
	}
}
