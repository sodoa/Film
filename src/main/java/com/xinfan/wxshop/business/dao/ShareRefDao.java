package com.xinfan.wxshop.business.dao;

import com.xinfan.wxshop.business.entity.ShareRef;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class ShareRefDao extends SqlSessionDaoSupport {
	
	public int updateByPrimaryKeySelective(ShareRef pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(ShareRef pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}
	
	public ShareRef selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
}