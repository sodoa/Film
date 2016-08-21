package com.xinfan.wxshop.business.dao;

import com.xinfan.wxshop.business.entity.ShareImage;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class ShareImageDao extends SqlSessionDaoSupport {
	
	public int updateByPrimaryKeySelective(ShareImage pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(ShareImage pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}
	
	public ShareImage selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
}