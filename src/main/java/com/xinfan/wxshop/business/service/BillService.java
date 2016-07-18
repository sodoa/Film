package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.cache.utils.ParamterUtils;
import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.BillDao;
import com.xinfan.wxshop.business.dao.CustomerDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Bill;
import com.xinfan.wxshop.business.entity.BillExample;
import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.util.QueryParamterUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;
import com.xinfan.wxshop.common.sms.SmsService;
import com.xinfan.wxshop.common.sms.YunpianSmsBean;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午5:30:14
 * 
 */
public class BillService {
	
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private BillDao billDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SmsService smsService;

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
		example.createCriteria().andOrderidEqualTo(orderNo);

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
			
			try{
				String smss= ParamterUtils.getString("order.pay.sms", "18673119686");
				String[] telphones = smss.split(",");
				for (String tel : telphones) {
					smsService.sendOderPaySms(tel, customer.getDisplayname() + " 付钱了，来米啦!" + bill.getAmount() +"$");
				}
			}
			catch(Exception e){
				logger.error(e.getMessage(),e);
			}

			return newExpiryDate;
		}

		return null;

	}

}
