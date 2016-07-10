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
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.SearchkeyService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午5:03:10
 * 
 */
@Controller
@RequestMapping("/admin/searchkey")
public class SearchkeyAction {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchkeyAction.class);

	@Autowired
	private	SearchkeyService searchkeyService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/searchkey/list");
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
		String word = request.getParameter("word");

		if (StringUtils.isNotEmpty(word)) {
			map.put("word", word);
		}

		page = searchkeyService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "searchkey_id", "word", "times", "create_time", "update_time"});

		return grid;
	}
	
	@RequestMapping("/delete.jspx")
	public @ResponseBody 
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String searchkeyId = request.getParameter("searchkeyId");

			if (StringUtils.isNotEmpty(searchkeyId)) {
				searchkeyService.deleteSearchkey(Integer.parseInt(searchkeyId));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
}
