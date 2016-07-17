package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Keymovie;
import com.xinfan.wxshop.business.entity.KeymovieExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class KeymovieDao extends SqlSessionDaoSupport {
	
	public List<DataMap> selectPageList(DataMap map, Pagination page) {
		return getSqlSession().selectList(wrapCommand("selectPageList"), map, page);
	}
	
	public List<Keymovie> selectByExample(KeymovieExample pojo) {
		return getSqlSession().selectList(wrapCommand("selectByExample"), pojo);
	}
	
	public int updateByPrimaryKeySelective(Keymovie pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(Keymovie pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}

	public Keymovie selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
}