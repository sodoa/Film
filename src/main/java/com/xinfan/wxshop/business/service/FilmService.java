package com.xinfan.wxshop.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.dao.FilmDao;
import com.xinfan.wxshop.business.util.QueryParamterUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月5日下午8:10:41
 * 
 */
public class FilmService {
	
	@Autowired
	private FilmDao filmDao;

	public Pagination selectPageList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");

		List list = filmDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
}
