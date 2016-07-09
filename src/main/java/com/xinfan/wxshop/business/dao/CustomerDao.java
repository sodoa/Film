/**
 * 
 */
package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.entity.CustomerExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;
import com.xinfan.wxshop.common.page.Pagination;

public class CustomerDao extends SqlSessionDaoSupport {

	public List<DataMap> pageList(DataMap map, Pagination page) {
		return getSqlSession().selectList(wrapCommand("pageList"), map, page);
	}

	public Integer count(DataMap map) {
		return getSqlSession().selectOne(wrapCommand("count"), map);
	}

	public List<DataMap> pageSelectCustomerList(DataMap map, Pagination page) {
		return getSqlSession().selectList(
				wrapCommand("pageSelectCustomerList"), map, page);
	}
	
	public List<DataMap> pageSelectLayerCustomerList(DataMap map, Pagination page) {
		return getSqlSession().selectList(
				wrapCommand("pageSelectLayerCustomerList"), map, page);
	}

	public int updateByPrimaryKeySelective(Customer pojo) {
		return getSqlSession().update(
				wrapCommand("updateByPrimaryKeySelective"), pojo);
	}

	public int insertSelective(Customer pojo) {
		return getSqlSession().update(wrapCommand("insertSelective"), pojo);
	}

	public int deleteByPrimaryKey(int id) {
		return getSqlSession().delete(wrapCommand("deleteByPrimaryKey"), id);
	}

	public Customer selectByPrimaryKey(int id) {
		return getSqlSession().selectOne(wrapCommand("selectByPrimaryKey"), id);
	}

	public Customer selectByAccount(String account) {

		CustomerExample example = new CustomerExample();
		example.createCriteria().andAccountEqualTo(account);
		List<Customer> list = getSqlSession().selectList(
				wrapCommand("selectByExample"), example);

		return list.isEmpty() ? null : list.get(0);
	}
	

	public Customer selectByWxId(String account) {

		CustomerExample example = new CustomerExample();
		example.createCriteria().andWxIdEqualTo(account);
		List<Customer> list = getSqlSession().selectList(
				wrapCommand("selectByExample"), example);

		return list.isEmpty() ? null : list.get(0);
	}
	
	
	
	public List<Customer> getListCustomerLayerUser(DataMap map) {
		return getSqlSession().selectList(wrapCommand("getListCustomerLayerUser"), map);
	}

}
