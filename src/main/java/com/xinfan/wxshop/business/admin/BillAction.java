package com.xinfan.wxshop.business.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.service.BillService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午5:43:24
 * 
 */
@Controller
@RequestMapping("/admin/bill")
public class BillAction {

	private static final Logger logger = LoggerFactory.getLogger(BillAction.class);
	
	@Autowired
	private	BillService billService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/bill/list");
		return mv;
	}
	
	@RequestMapping("/page.jspx")
	public @ResponseBody
	DataTableDataGrid waitOrderPage(HttpServletRequest request) {

		Pagination page = RequestUtils.getDataTablePagination(request);

		String draw = request.getParameter("draw");
		if (draw == null || draw.trim().length() == 0) {
			draw = "1";
		}
		
		DataMap map = new DataMap();
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		if (StringUtils.isNotEmpty(startdate)) {
			map.put("startdate", startdate);
		}
		if (StringUtils.isNotEmpty(enddate)) {
			map.put("enddate", enddate);
		}

		page = billService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "bill_id", "amount", "paytime", "orderid", "customer_id","wx_id"});

		return grid;
	}
}
