package com.xinfan.wxshop.business.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.dao.ShareRefDao;
import com.xinfan.wxshop.business.entity.ShareRef;


public class ShareRefService {

	@Autowired
	private ShareRefDao ShareRefDao;

	@Autowired
	private SequenceDao sequenceDao;

	public ShareRef get(Integer id) {
		return ShareRefDao.selectByPrimaryKey(id);
	}

	public void deleteMovie(Integer movieId) {
		ShareRefDao.deleteByPrimaryKey(movieId);
	}

	public int insert(ShareRef pojo) {
		int refid = this.sequenceDao.getSequence(SequenceConstants.SEQ_SHARE_REF);
		pojo.setRefid(refid);
		pojo.setCreatedate(new Date());
		ShareRefDao.insertSelective(pojo);
		return refid;
	}

}
