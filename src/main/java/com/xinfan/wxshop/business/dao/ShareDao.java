package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.business.entity.ShareExample;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class ShareDao extends SqlSessionDaoSupport {

	public int updateByPrimaryKeySelective(Share pojo) {
		return getSqlSession().update(wrapCommand("updateByPrimaryKeySelective"), pojo);
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

	public List<Share> listShare() {
		ShareExample share = new ShareExample();
		share.setOrderByClause("online desc ,createdate desc");
		return getSqlSession().selectList(wrapCommand("selectByExample"), share);
	}

}