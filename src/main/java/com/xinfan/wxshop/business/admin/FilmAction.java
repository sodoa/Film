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

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.FilmService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月3日下午11:00:09
 * 
 */
@Controller
@RequestMapping("/admin/film")
public class FilmAction {
	
	private static final Logger logger = LoggerFactory.getLogger(FilmAction.class);
	
	@Autowired
	private FilmService filmService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/list");
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

		String name = request.getParameter("name");

		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}

		page = filmService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "film_id", "name", "resume","type", "director",
				"actor","country","publish"});

		return grid;
	}
	
	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/add");
		return mv;
	}
	
	@RequestMapping("/add-save.jspx")
	public ModelAndView addsave(HttpServletRequest request,Film film) {
		ModelAndView mv = new ModelAndView("/admin/film/tip");
		return mv;
	}
	
	@RequestMapping("/delete.jspx")
	public @ResponseBody 
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

//		try {
//
//			String customerId = request.getParameter("id");
//
//			if (StringUtils.isNotEmpty(customerId)) {
//				CustomerService.deleteCustomer(Integer.parseInt(customerId));
//			}
//
//			result = result.success();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			result = result.error("删除失败");
//		}
		return result;
	}
	
	@RequestMapping("/update.jspx")
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/update");
		return mv;
	}
	
	@RequestMapping("/update-save.jspx")
	public ModelAndView updatesave(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/tip");
		return mv;
	}

}
