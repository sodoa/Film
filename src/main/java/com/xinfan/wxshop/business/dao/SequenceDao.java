/**
 * 
 */
package com.xinfan.wxshop.business.dao;

import java.util.HashMap;
import java.util.Map;

import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class SequenceDao extends SqlSessionDaoSupport {

	public int getSequence(String name) {
		Map map = new HashMap();
		map.put("name", name);
		return getSqlSession().selectOne(wrapCommand("getNextValue"), map);
	}

}
