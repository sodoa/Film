package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.BillDao;
import com.xinfan.wxshop.business.dao.CustomerDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Bill;
import com.xinfan.wxshop.business.entity.BillExample;
import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.entity.MovieExample;
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

	@Autowired
	private CustomerDao customerDao;

	public Pagination selectPageList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");

		List list = billDao.selectPageList(map, page);
		page.setList(list);

		return page;
	}

	public void deleteBill(Integer billId) {
		billDao.deleteByPrimaryKey(billId);
	}

	public Bill getMovie(Integer billId) {
		return billDao.selectByPrimaryKey(billId);
	}

	public void addOrder(Bill bill) {
		int billId = this.sequenceDao.getSequence(SequenceConstants.SEQ_BILL);
		bill.setBillId(billId);
		bill.setState(2);
		billDao.insertSelective(bill);
	}

	public Date updateOrderIsPay(String orderNo) {
		BillExample example = new BillExample();
		example.createCriteria().andOrdernoEqualTo(orderNo);

		List<Bill> bills = billDao.selectByExample(example);
		if (bills.size() == 1) {

			Bill bill = bills.get(0);
			Integer customerId = bill.getCustomerId();
			Customer customer = customerDao.selectByPrimaryKey(customerId);
			Date expiryDate = customer.getExpirydate();
			if (expiryDate == null) {
				expiryDate = new Date();
			} else {
				expiryDate = new Date();
			}

			Date newExpiryDate = DateUtils.addMonths(expiryDate, 1);

			Customer update = new Customer();
			update.setCustomerId(customer.getCustomerId());
			update.setExpirydate(newExpiryDate);
			customerDao.updateByPrimaryKeySelective(update);
			
			Bill updateBill = new Bill();
			updateBill.setBillId(bill.getBillId());
			updateBill.setState(1);
				
			billDao.updateByPrimaryKeySelective(updateBill);

			return newExpiryDate;
		}

		return null;

	}

}
