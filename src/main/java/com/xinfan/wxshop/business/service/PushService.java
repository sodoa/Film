package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.FilmDao;
import com.xinfan.wxshop.business.dao.PushDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Push;
import com.xinfan.wxshop.business.entity.PushExample;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午1:01:54
 * 
 */
public class PushService {
	
	@Autowired
	private PushDao pushDao;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	public Pagination selectPageList(DataMap map, Pagination page) {

		List list = pushDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public Push getPush(Integer PushId){
		return pushDao.selectByPrimaryKey(PushId);
	}
	
	public void deletePush(Integer PushId){
		pushDao.deleteByPrimaryKey(PushId);
	}
	
	public void savePush(Push push){
		int pushId = this.sequenceDao.getSequence(SequenceConstants.SEQ_PUSH);
		push.setPushId(pushId);
		push.setCreatetime(new Date());
		
		pushDao.insertSelective(push);
	}
	
	public void updatePush(Push Push){
		pushDao.updateByPrimaryKeySelective(Push);
	}

}
