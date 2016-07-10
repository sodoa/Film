package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class FilmDao extends SqlSessionDaoSupport {
	
	public List<DataMap> selectPageList(DataMap map, Pagination page) {
		return getSqlSession().selectList(wrapCommand("selectPageList"), map, page);
	}
	
	public int updateByPrimaryKeySelective(Film pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(Film pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}

	public Film selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
}