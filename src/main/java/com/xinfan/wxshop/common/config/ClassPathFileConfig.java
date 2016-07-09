package com.xinfan.wxshop.common.config;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * 文件配置管理
 * 
 * @author cyp
 * 
 */
public class ClassPathFileConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassPathFileConfig.class);

	public static File getClassPathFile(String filename) {

		ClassPathResource loader = new ClassPathResource(filename);
		try {
			return loader.getFile();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

}
