package com.xinfan.wxshop.business.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.FilmService;
import com.xinfan.wxshop.business.service.MovieService;
import com.xinfan.wxshop.business.util.FilePathHelper;
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
	
	//数据绑定  
    @InitBinder    
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
	
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
		
		String notin = request.getParameter("notin");
		if (StringUtils.isNotEmpty(notin)) {
			if(notin.trim().startsWith(",")){
				notin = notin.trim().substring(1);
			}
			
			map.put("notin", notin);
		}

		page = filmService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "film_id", "name","type", "director",
				"actor","country","publish","count"});

		return grid;
	}
	
	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/add");
		String time = String.valueOf(new Date().getTime());
		mv.addObject("time", time);
		return mv;
	}
	
	@RequestMapping("/add-save.jspx")
	public ModelAndView addsave(HttpServletRequest request,Film film) {
		ModelAndView mv = new ModelAndView("/admin/film/tip");
		
		String thumdPath = FilePathHelper.getImageThumdUploadPath(request);
		Collection<File> thumdFile = FileUtils.listFiles(new File(thumdPath), new String[] { "jpg" }, true);

		int index = 0;
		if (!thumdFile.isEmpty()) {
			File thumdImage = thumdFile.iterator().next();
			film.setPicture(thumdImage.getPath().split("film")[1]);
		}
		
		filmService.saveFilm(film);
		mv.addObject("msg", "添加成功");
		return mv;
	}
	
	@RequestMapping("/delete.jspx")
	public @ResponseBody 
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String filmId = request.getParameter("filmId");

			if (StringUtils.isNotEmpty(filmId)) {
				filmService.deleteFilm(Integer.parseInt(filmId));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/update.jspx")
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/film/update");
		String filmId = request.getParameter("filmId");

		if (StringUtils.isNotEmpty(filmId)) {
			Film film = filmService.getFilm(Integer.parseInt(filmId));
			mv.addObject("film", film);
		}
		return mv;
	}
	
	@RequestMapping("/update-save.jspx")
	public ModelAndView updatesave(HttpServletRequest request,Film film) {
		ModelAndView mv = new ModelAndView("/admin/film/tip");
		
		filmService.updateFilm(film);
		mv.addObject("msg", "修改成功");
		return mv;
	}

}
