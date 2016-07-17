package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Bill;
import com.xinfan.wxshop.business.entity.BillExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class BillDao extends SqlSessionDaoSupport {
	

	public List<DataMap> selectPageList(DataMap map, Pagination page) {
		return getSqlSession().selectList(wrapCommand("selectPageList"), map, page);
	}
	
	public List<Bill> selectByExample(BillExample pojo) {
		return getSqlSession().selectList(wrapCommand("selectByExample"), pojo);
	}
	
	public int updateByPrimaryKeySelective(Bill pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}
	
	public int insertSelective(Bill pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}

	public Bill selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}
	
}