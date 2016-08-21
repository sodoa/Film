package com.xinfan.wxshop.business.dao;

import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class ShareDao extends SqlSessionDaoSupport {
	
	public int updateByPrimaryKeySelective(Share pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(Share pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}
	
	public Share selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
}