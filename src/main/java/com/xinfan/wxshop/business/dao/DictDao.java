/**
 * 
 */
package com.xinfan.wxshop.business.dao;

import java.util.List;

import com.xinfan.wxshop.business.entity.Dict;
import com.xinfan.wxshop.business.entity.DictExample;
import com.xinfan.wxshop.common.dal.SqlSessionDaoSupport;

public class DictDao extends SqlSessionDaoSupport {

	public List<Dict> selectAll() {
		DictExample example = new DictExample();
		return getSqlSession().selectList(wrapCommand("selectByExample"), example);
	}

}
