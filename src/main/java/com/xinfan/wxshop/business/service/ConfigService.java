/**
 * 
 */
package com.xinfan.wxshop.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.dao.ConfigDao;
import com.xinfan.wxshop.business.entity.Config;

public class ConfigService {
	
	@Autowired
	private ConfigDao configDao;

	public List<Config> getList(){
		return configDao.getList();
	}
	
	public void update(Config config){
		configDao.updateByPrimaryKeySelective(config);
	}
}
