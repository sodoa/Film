package com.xinfan.wxshop.business.admin;

import java.util.ArrayList;
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

import com.xinfan.wxshop.business.entity.Movie;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.MovieService;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月10日下午1:01:05
 * 
 */
@Controller
@RequestMapping("/admin/movie")
public class MovieAction {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieAction.class);

	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/movie/list");
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
		String type = request.getParameter("type");

		if (StringUtils.isNotEmpty(name)) {
			map.put("name", name);
		}
		map.put("type", type);

		page = movieService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "movie_id", "film_id", "name","picture"});

		return grid;
	}
	
	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/movie/add");
		String type = request.getParameter("type");
		if (StringUtils.isNotEmpty(type)) {
			DataMap mmap = new DataMap();
			mmap.put("type", type);
			List<Movie> list = movieService.selectList(mmap);
			if(CollectionUtils.isNotEmpty(list)){
				StringBuffer sb = new StringBuffer();	
				for(Movie m:list){
					sb.append(m.getFilmId()).append(",");
				}
				mv.addObject("notin", sb.deleteCharAt(sb.length() - 1).toString());
			}
		}
		
		return mv;
	}
	
	@RequestMapping("/add-save.jspx")
	public @ResponseBody 
	JSONResult addsave(HttpServletRequest request,Movie movie) {
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
	

}
