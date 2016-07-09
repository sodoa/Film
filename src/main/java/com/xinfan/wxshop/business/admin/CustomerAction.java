package com.xinfan.wxshop.business.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Customer;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.CustomerService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

@Controller
@RequestMapping("/admin")
public class CustomerAction {

	private static final Logger logger = LoggerFactory.getLogger(CustomerAction.class);

	@Autowired
	private CustomerService CustomerService;

	@RequestMapping("/customer-list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/customer/list");
		return mv;
	}

	@RequestMapping("/customer-layer-list.jspx")
	public ModelAndView customerLayerList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/customer/list-layer");
		return mv;
	}
	
//	@RequestMapping("/set-virtual-order.jspx")
//	public ModelAndView setVirtualOrder(HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("/admin/customer/set-virtual-order");
//		int customerId = Integer.parseInt(request.getParameter("cid"));
//		mv.addObject("customerId", customerId);
//		return mv;
//	}
//	
//
//	@RequestMapping("/post-virtual-order.jspx")
//	public @ResponseBody
//	JSONResult postVirtualOrder(HttpServletRequest request) {
//		
//		try{
//			int customerId = Integer.parseInt(request.getParameter("customerId"));
//			float amount = Integer.parseInt(request.getParameter("amount"));
//			String chargeName = request.getParameter("chargeName");
//			
//			this.DistributionService.addCustomerVirtualDistribution(customerId, amount, chargeName);
//			return JSONResult.success();
//		}catch(Exception e){
//			logger.error(e.getMessage(),e);
//			return JSONResult.error(e.getMessage());
//		}
//
//	}

//	@RequestMapping("/customer-vir-disr.jspx")
//	public ModelAndView virialDisr(HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("/admin/customer/customer-vir-disr");
//
//		int customerId = Integer.parseInt(request.getParameter("id"));
//		Pagination page = new Pagination();
//		page.setPageNo(1);
//		page.setPageSize(100);
//
//		page = this.DistributionService.pageNearCustomerVirtualDistributionList(customerId, page);
//
//		mv.addObject("list1", page.getList());
//
//		return mv;
//	}

	@RequestMapping("/customer-layer.jspx")
	public ModelAndView customerLayert(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/customer/customer-layer");

		int customerId = Integer.parseInt(request.getParameter("id"));

		List<Customer> list1 = CustomerService.listCustomerLayerUser(customerId, 1);
		List<Customer> list2 = CustomerService.listCustomerLayerUser(customerId, 2);

		mv.addObject("list1", list1);
		mv.addObject("list2", list2);
		mv.addObject("customerId", customerId);

		return mv;
	}

//	@RequestMapping("/customer-distriction-list.jspx")
//	public ModelAndView customerDistrictionList(HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("/admin/customer/list-disrition");
//
//		String type = request.getParameter("type");
//
//		if (type == null) {
//			type = "1";
//		}
//
////		List list = DistrRankDao.getDistrRankListByType(type);
//
//		mv.addObject("type", type);
////		mv.addObject("list", list);
//
//		return mv;
//	}

	@RequestMapping("/customer-password.jspx")
	public ModelAndView password(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/customer/password");

		String customerId = request.getParameter("id");

		Customer bean = this.CustomerService.getById(Integer.parseInt(customerId));
		mv.addObject("bean", bean);

		return mv;
	}

	@RequestMapping("/customer-info.jspx")
	public ModelAndView info(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/customer/info");
		String customerId = request.getParameter("id");

		Customer bean = this.CustomerService.getById(Integer.parseInt(customerId));
//		Wallet wallet = this.CustomerService.getWalletByCustomerId(Integer.parseInt(customerId));

		mv.addObject("bean", bean);
//		mv.addObject("wallet", wallet);

		return mv;
	}

	@RequestMapping("/customer-spassword.jspx")
	public void spassword(HttpServletRequest request,HttpServletResponse response) {
		String customerId = request.getParameter("id");
		String newPassword = request.getParameter("new-password");
		CustomerService.updateCustomerPassword(Integer.parseInt(customerId), newPassword);
	}

	@RequestMapping("/customer-list-page.jspx")
	public @ResponseBody
	DataTableDataGrid waitOrderPage(HttpServletRequest request) {

		Pagination page = RequestUtils.getDataTablePagination(request);
		String draw = request.getParameter("draw");
		if (draw == null || draw.trim().length() == 0) {
			draw = "1";
		}

		DataMap map = new DataMap();

		String userPhone = request.getParameter("user_phone");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");

		if (StringUtils.isNotEmpty(userPhone)) {
			map.put("account", userPhone);
		}
		if (StringUtils.isNotEmpty(startdate)) {
			map.put("startdate", startdate);
		}
		if (StringUtils.isNotEmpty(enddate)) {
			map.put("enddate", enddate);
		}

		page = CustomerService.pageSelectCustomerList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "customer_id", "displayname", "account","wx_id", "regdate",
				"expirydate","reg_type", "state" });

		return grid;
	}

	@RequestMapping("/customer-listlayer-page.jspx")
	public @ResponseBody
	DataTableDataGrid listlayer(HttpServletRequest request) {

		Pagination page = RequestUtils.getDataTablePagination(request);
		String draw = request.getParameter("draw");
		if (draw == null || draw.trim().length() == 0) {
			draw = "1";
		}

		DataMap map = new DataMap();

		String userPhone = request.getParameter("user_phone");
		String level2Num = request.getParameter("level2Num");

		if (StringUtils.isNotEmpty(userPhone)) {
			map.put("account", userPhone);
		}
		if (StringUtils.isNotEmpty(level2Num)) {
			map.put("level2Num", level2Num);
		}

		page = CustomerService.pageSelectLayerCustomerList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page, new String[] { "customer_id", "displayname", "account", "regdate",
				"reg_type", "level2" });

		return grid;
	}

	@RequestMapping("/customer-delete.jspx")
	public @ResponseBody
	JSONResult deleteCustomer(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String customerId = request.getParameter("id");

			if (StringUtils.isNotEmpty(customerId)) {
				CustomerService.deleteCustomer(Integer.parseInt(customerId));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}

	@RequestMapping("/customer-state.jspx")
	public @ResponseBody
	JSONResult stateCustomer(HttpServletRequest request) {
		JSONResult result = new JSONResult();
		try {
			String customerId = request.getParameter("id");
			String state = request.getParameter("state");

			if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotBlank(state)) {
				CustomerService.updateCustomerState(Integer.parseInt(customerId), Integer.parseInt(state));
				result = result.success();
			} else {
				result.error("参数为空");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}

}
