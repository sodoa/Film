/**
 * 
 */
package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Config;
import com.xinfan.wxshop.business.entity.ConfigExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class ConfigDao extends SqlSessionDaoSupport {

	public List<DataMap> pageList(DataMap map ,Pagination page) {
		return getSqlSession().selectList(wrapCommand("pageList"), map,page);
	}

	public int updateByPrimaryKeySelective(Config pojo) {
		return getSqlSession().update(wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public  int insertSelective(Config pojo){
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}
	
	public  int deleteByPrimaryKey(int id){
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}
	
	public  Config selectByPrimaryKey(int id){
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
	
	public List<Config> getList() {
		ConfigExample example = new ConfigExample();
		example.setOrderByClause("id");
		return getSqlSession().selectList(wrapCommand("selectByExample"), example);
	}
	
}
