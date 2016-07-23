package com.xinfan.wxshop.business.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Film;
import com.xinfan.wxshop.business.entity.Keymovie;
import com.xinfan.wxshop.business.entity.Movie;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.KeymovieService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午1:01:05
 * 
 */
@Controller
@RequestMapping("/admin/keymovie")
public class KeymovieAction {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieAction.class);

	@Autowired
	private KeymovieService movieService;

	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/keymovie/list");
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
		String word = request.getParameter("word");

		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		if (StringUtils.isNotEmpty(word)) {
			map.put("word", word);
		}

		page = movieService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "keymovie_id", "film_id", "name", "word"});

		return grid;
	}
	
	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/keymovie/add");
		
		DataMap mmap = new DataMap();
		List<Keymovie> list = movieService.selectList(mmap);
		if(CollectionUtils.isNotEmpty(list)){
			StringBuffer sb = new StringBuffer();	
			for(Keymovie m:list){
				sb.append(m.getFilmId()).append(",");
			}
			mv.addObject("notin", sb.deleteCharAt(sb.length() - 1).toString());
		}
		
		return mv;
	}
	
	@RequestMapping("/add-save.jspx")
	public @ResponseBody 
	JSONResult addsave(HttpServletRequest request,Keymovie movie) {
		JSONResult result = new JSONResult();
		try {

			movieService.saveMovie(movie);

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/delete.jspx")
	public @ResponseBody 
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String movieId = request.getParameter("movieId");

			if (StringUtils.isNotEmpty(movieId)) {
				movieService.deleteMovie(Integer.parseInt(movieId));
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
		ModelAndView mv = new ModelAndView("/admin/keymovie/update");
		String movieId = request.getParameter("movieId");

		if (StringUtils.isNotEmpty(movieId)) {
			Keymovie keymovie = movieService.getMovie(Integer.parseInt(movieId));
			mv.addObject("keymovie", keymovie);
		}
		return mv;
	}
	
	@RequestMapping("/update-save.jspx")
	public ModelAndView updatesave(HttpServletRequest request,Keymovie film) {
		ModelAndView mv = new ModelAndView("/admin/keymovie/tip");
		
		movieService.updateMovie(film);
		mv.addObject("msg", "设置成功");
		return mv;
	}
}
