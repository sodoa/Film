package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.SearchkeyDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Searchkey;
import com.xinfan.wxshop.business.entity.SearchkeyExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午5:04:28
 * 
 */
public class SearchkeyService {

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private SearchkeyDao searchkeyDao;
	
	public Pagination selectPageList(DataMap map, Pagination page) {

		List list = searchkeyDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public void deleteSearchkey(Integer searchkeyId){
		searchkeyDao.deleteByPrimaryKey(searchkeyId);
	}
	
	public List<Searchkey> selectList(DataMap map){
		SearchkeyExample example = new SearchkeyExample();
		if(StringUtils.isNotBlank(map.getString("word"))){
			example.createCriteria().andWordEqualTo(map.getString("word"));	
		}
		
		return searchkeyDao.selectByExample(example);
	}
	
	public Searchkey getSearchkey(Integer movieId){
		return searchkeyDao.selectByPrimaryKey(movieId);
	}
	
	
	public void saveSearchkey(Searchkey searchkey){
		int movieId = this.sequenceDao.getSequence(SequenceConstants.SEQ_SEARCHKEY);
		searchkey.setSearchkeyId(movieId);
		searchkey.setCreateTime(new Date());
		searchkey.setUpdateTime(new Date());

		searchkeyDao.insertSelective(searchkey);
	}
	
	public void updateMovie(Searchkey movie){
		searchkeyDao.updateByPrimaryKeySelective(movie);
	}

}
