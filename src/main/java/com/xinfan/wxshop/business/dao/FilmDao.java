package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class FilmDao extends SqlSessionDaoSupport {
	
	public List<DataMap> selectPageList(DataMap map, Pagination page) {
		return getSqlSession().selectList(wrapCommand("selectPageList"), map, page);
	}
}