package com.xinfan.wxshop.business.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.dao.ShareDao;
import com.xinfan.wxshop.business.dao.ShareImageDao;
import com.xinfan.wxshop.business.entity.Share;

public class ShareService {

	@Autowired
	private ShareDao ShareDao;

	@Autowired
	private ShareImageDao ShareImageDao;

	@Autowired
	private SequenceDao sequenceDao;

	public Share getShare(Integer id) {
		return ShareDao.selectByPrimaryKey(id);
	}

	public void deleteShare(Integer id) {
		ShareDao.deleteByPrimaryKey(id);
	}

	public int insertShare(Share pojo) {
		int id = this.sequenceDao.getSequence(SequenceConstants.SEQ_SHARE);
		pojo.setShareid(id);
		pojo.setCreatedate(new Date());
		ShareDao.insertSelective(pojo);
		return id;
	}

}
