package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.dao.ShareDao;
import com.xinfan.wxshop.business.dao.ShareImageDao;
import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.business.entity.ShareImage;

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

	public List<Share> listShare() {
		return ShareDao.listShare();
	}
	
	public List<ShareImage> listImageShare(int shareid) {
		return ShareImageDao.listImageShare(shareid);
	}


	public int insertShare(Share pojo) {
		int id = this.sequenceDao.getSequence(SequenceConstants.SEQ_SHARE);
		pojo.setShareid(id);
		pojo.setCreatedate(new Date());
		pojo.setSmnt(0);
		pojo.setOnline(1);
		ShareDao.insertSelective(pojo);
		return id;
	}
	
	public void updateShare(Share pojo) {
		ShareDao.updateByPrimaryKeySelective(pojo);
	}
	
	public void deleteShareImage(Integer id) {
		ShareImageDao.deleteByPrimaryKey(id);
	}
	
	public int insertShareImage(ShareImage pojo) {
		int id = this.sequenceDao.getSequence(SequenceConstants.SEQ_SHARE_IMAGE);
		pojo.setImageid(id);
		ShareImageDao.insertSelective(pojo);
		return id;
	}

}
