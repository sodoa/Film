package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.dao.ShareDao;
import com.xinfan.wxshop.business.dao.ShareImageDao;
import com.xinfan.wxshop.business.dao.ShareRefDao;
import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.business.entity.ShareImage;
import com.xinfan.wxshop.business.entity.ShareRef;

public class ShareService {

	@Autowired
	private ShareDao ShareDao;

	@Autowired
	private ShareImageDao ShareImageDao;
	
	@Autowired
	private ShareRefDao ShareRefDao;

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
	
	public void updateShareCount(int refId) {
		
		ShareRef ref = ShareRefDao.selectByPrimaryKey(refId);
		
		Share share = this.ShareDao.selectByPrimaryKey(ref.getShareid());
		
		Share updateShare  = new Share();
		updateShare.setShareid(ref.getShareid());
		updateShare.setSmnt(share.getSmnt()+1);
		
		ShareDao.updateByPrimaryKeySelective(updateShare);
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
	
	public Share randomShare() {
		List<Share> list = ShareDao.listOnlineShare();
		
		if(list.size()>0){
			int index = new Random().nextInt(list.size());
			return list.get(index);
		}
		
		return null;
	}
	

}
