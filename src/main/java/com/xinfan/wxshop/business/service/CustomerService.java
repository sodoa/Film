package com.xinfan.wxshop.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinfan.wxshop.business.constants.SequenceConstants;
import com.xinfan.wxshop.business.dao.CustomerDao;
import com.xinfan.wxshop.business.dao.SequenceDao;
import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.pay.weixin.WxNotifyUtils;
import com.xinfan.wxshop.business.util.ConfigUtils;
import com.xinfan.wxshop.business.util.QueryParamterUtils;
import com.xinfan.wxshop.common.base.BizException;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;
import com.xinfan.wxshop.common.security.Md5PwdEncoder;

public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerDao customerDao;
	
//	@Autowired
//	private WxShareService WxShareService;
//
//	@Autowired
//	private GoodsDao goodsDao;
//
//	@Autowired
//	private CartDao cartDao;
//
//	@Autowired
//	private OrderDao orderDao;
//
//	@Autowired
//	private OrderDetailDao orderDetailDao;

//	@Autowired
//	private WalletDao walletDao;
//
//	@Autowired
//	private DistributionDao distributionDao;

	@Autowired
	private SequenceDao sequenceDao;

//	@Autowired
//	private TransferDao transferDao;

	public Customer login(String account, String password, Map attributes) {

		if (account == null) {
			return null;
		}

		account = account.trim().toLowerCase();
		Customer customer = customerDao.selectByAccount(account);

		if (customer == null) {
			throw new BizException(100, "用户不存在");
		}

		if (customer.getState() != 1) {
			throw new BizException(102, "帐户状态异常");
		}

		Md5PwdEncoder encoder = new Md5PwdEncoder();

		boolean bool = encoder.isPasswordValid(customer.getPwd(), password);
		if (!bool) {
			throw new BizException(101, "密码不正确");
		}

		return customer;
	}

	public Customer regist(String account, String password, String displayName, String wxId,String sex) {

		if (sex == null || sex.trim().length() == 0) {
			sex = "1";
		}
		
		Customer exist = customerDao.selectByWxId(wxId);
		if (exist != null) {
//			if(!displayName.equals(exist.getDisplayname())){
//				exist.setDisplayname(displayName);
//				exist.setSex(Integer.parseInt(sex));
//				customerDao.updateByPrimaryKeySelective(exist);
//			}
			
			return exist;
		}

		int customerId = this.sequenceDao.getSequence(SequenceConstants.SEQ_CUSTOMER);

		Customer bean = new Customer();
		bean.setAccount(account.trim());
		bean.setPwd(new Md5PwdEncoder().encodePassword(password));
		bean.setDisplayname(displayName);
		bean.setRegdate(new Date());
		bean.setSex(Integer.parseInt(sex));
		bean.setCustomerId(customerId);
		bean.setWxId(wxId);
		bean.setExpirydate(DateUtils.addDays(new Date(), 3));
		bean.setRegType(1);

		this.customerDao.insertSelective(bean);
		
		return bean;
		
		//WxNotifyUtils.customerDownlineJoinNotify(shareCustomer.getWxId(), displayName, "2");
	}

	public Customer getByAccount(String account) {
		Customer bean = customerDao.selectByAccount(account.trim());
		return bean;
	}

	public Customer getByWeixinId(String wxId) {
		Customer bean = customerDao.selectByWxId(wxId.trim());
		return bean;
	}
	
	public Customer getById(int id) {
		Customer bean = customerDao.selectByPrimaryKey(id);
		return bean;
	}

	public void deleteCustomer(int id) {

		Customer customer = customerDao.selectByPrimaryKey(id);
		if (customer != null) {
			this.customerDao.deleteByPrimaryKey(id);
		}
	}

	public void updateCustomerState(int id, int state) {

		Customer customer = customerDao.selectByPrimaryKey(id);
		if (customer != null) {

			Customer update = new Customer();
			update.setCustomerId(id);
			update.setState(state);
			this.customerDao.updateByPrimaryKeySelective(update);
		}
	}
	
	public void updateRewardData(int id) {

		Customer customer = customerDao.selectByPrimaryKey(id);
		if (customer != null) {
			Customer update = new Customer();
			update.setCustomerId(id);
			int rewardData = Integer.valueOf(ConfigUtils.getValue("rewardDate"));
			
			Date now = new Date();
			if(customer.getExpirydate().before(now)){
				update.setExpirydate(DateUtils.addDays(now, rewardData));
			}else{
				update.setExpirydate(DateUtils.addDays(customer.getExpirydate(), rewardData));
			}
			
			this.customerDao.updateByPrimaryKeySelective(update);
		}
	}

	public void updateCustomerPassword(int id, String newOrgiPassword) {

		Customer customer = customerDao.selectByPrimaryKey(id);
		if (customer != null) {
			Customer update = new Customer();
			update.setCustomerId(id);
			update.setPwd(new Md5PwdEncoder().encodePassword(newOrgiPassword));
			this.customerDao.updateByPrimaryKeySelective(update);
		}
	}

	public void updateCustomerForgetPassword(String account, String newOrgiPassword) {

		Customer customer = customerDao.selectByAccount(account);
		if (customer == null) {
			throw new BizException("用户不存在");
		}

		if (customer != null) {
			Customer update = new Customer();
			update.setCustomerId(customer.getCustomerId());
			update.setPwd(new Md5PwdEncoder().encodePassword(newOrgiPassword));
			this.customerDao.updateByPrimaryKeySelective(update);
		}
	}

	public void updateResetCustomerPassword(Integer customerId, String newOrgiPassword, String oldOrgiPassword) {

		Customer customer = customerDao.selectByPrimaryKey(customerId);
		if (customer == null) {
			throw new BizException("用户不存在");
		}

		String encPwd = customer.getPwd();
		String newEncPwd = new Md5PwdEncoder().encodePassword(newOrgiPassword);
		String oldEncPwd = new Md5PwdEncoder().encodePassword(oldOrgiPassword);

		if (!encPwd.equals(oldEncPwd)) {
			throw new BizException("原始密码错误");
		}

		Customer update = new Customer();
		update.setCustomerId(customer.getCustomerId());
		update.setPwd(newEncPwd);
		this.customerDao.updateByPrimaryKeySelective(update);

	}

	public Pagination pageSelectCustomerList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");

		List list = this.customerDao.pageSelectCustomerList(map, page);
		page.setList(list);

		return page;
	}

	public Pagination pageSelectLayerCustomerList(DataMap map, Pagination page) {

		QueryParamterUtils.addQueryTime(map, "startdate", "endate");

		List list = this.customerDao.pageSelectLayerCustomerList(map, page);
		page.setList(list);

		return page;
	}

	public List<Customer> listCustomerLayerUser(int customerId, int level) {
		DataMap param = new DataMap();
		param.put("customerId", customerId);
		param.put("level", level);

		List<Customer> list = customerDao.getListCustomerLayerUser(param);
		return list;
	}

}
