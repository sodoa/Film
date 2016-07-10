package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.BillDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.MovieExample;
import com.xinfan.wxshop.business.entity.Bill;
import com.xinfan.wxshop.business.util.QueryParamterUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午5:30:14
 * 
 */
public class BillService {

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private BillDao billDao;
	
	public Pagination selectPageList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");
		
		List list = billDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}
	
	public void deleteBill(Integer billId){
		billDao.deleteByPrimaryKey(billId);
	}
	
	public List<Bill> selectList(DataMap map){
		MovieExample example = new MovieExample();
		if(StringUtils.isNotBlank(map.getString("word"))){
			example.createCriteria().andTypeEqualTo(map.getInt("word"));	
		}
		
		return billDao.selectByExample(example);
	}
	
	public Bill getMovie(Integer billId){
		return billDao.selectByPrimaryKey(billId);
	}
	
	
	public void saveMovie(Bill bill){
		int billId = this.sequenceDao.getSequence(SequenceConstants.SEQ_BILL);
		bill.setBillId(billId);
		
		billDao.insertSelective(bill);
	}
	
	public void updateMovie(Bill bill){
		billDao.updateByPrimaryKeySelective(bill);
	}

}
