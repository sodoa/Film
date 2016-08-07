package com.xinfan.wxshop.business.admin;

import java.io.File;
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

import com.github.sd4324530.fastweixin.api.MediaAPI;
import com.github.sd4324530.fastweixin.api.MessageAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Article;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.GetSendMessageResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMediaResponse;
import com.github.sd4324530.fastweixin.message.MpNewsMsg;
import com.xinfan.wxshop.business.entity.Push;
import com.xinfan.wxshop.business.entity.PushMovie;
import com.xinfan.wxshop.business.model.DataTableDataGrid;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.PushMovieService;
import com.xinfan.wxshop.business.service.PushService;
import com.xinfan.wxshop.business.util.FilePathHelper;
import com.xinfan.wxshop.business.util.RequestUtils;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.config.FileConfig;
import com.xinfan.wxshop.common.page.Pagination;

/**
 * @author huangmin
 * @DATE 2016年7月26日下午9:20:58
 * 
 */
@Controller
@RequestMapping("/admin/push")
public class PushMovieAction {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieAction.class);

	@Autowired
	private PushService pushService;
	
	@Autowired
	private PushMovieService pushmovieService;
	
	@RequestMapping("/list.jspx")
	public ModelAndView waitOrder(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/pushmovie/list");
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


		page = pushService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "push_id", "instruction", "createtime","status"});

		return grid;
	}
	
	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/pushmovie/add");
		return mv;
	}
	
	@RequestMapping("/add-save.jspx")
	public ModelAndView addsave(HttpServletRequest request,Push push) {
		ModelAndView mv = new ModelAndView("/admin/film/tip");

		pushService.savePush(push);
		mv.addObject("msg", "添加成功");
		return mv;
	}
	
	@RequestMapping("/delete.jspx")
	public @ResponseBody 
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String pushId = request.getParameter("pushId");

			if (StringUtils.isNotEmpty(pushId)) {
				pushService.deletePush(Integer.parseInt(pushId));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/push.jspx")
	public @ResponseBody 
	JSONResult push(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {
			String pushId = request.getParameter("pushId");

			String path = FilePathHelper.getFileStoreMainPath();
			
			System.out.println(path);
			if (StringUtils.isNotEmpty(pushId)) {
				DataMap map = new DataMap();
				map.put("pushId", pushId);
				List<PushMovie> movelist = pushmovieService.selectList(map);
				
				if(CollectionUtils.isNotEmpty(movelist)){

					ApiConfig config = new ApiConfig(FileConfig.getInstance().getString("weixin.appid"),
							FileConfig.getInstance().getString("weixin.appsecret"));
					MediaAPI mediaAPI = new MediaAPI(config);
					
					
					if(movelist.size() > 10){
						movelist = movelist.subList(0, 10);
					}
					
					
					
					
					List<Article> list = new ArrayList<Article>();
					for(PushMovie movie : movelist){
						UploadMediaResponse response = mediaAPI.uploadMedia(MediaType.IMAGE, new File(path + movie.getPicture()));
				        String media_id = response.getMediaId();
				        Article article = new Article(media_id, "爆品电影", movie.getName(), "http://www.11grand.cn/film/movie/see.jspx?fid="+movie.getFilmId()
				        		,movie.getResume() +"<br>点击“阅读原文”开始开电影!" , movie.getName(), Article.ShowConverPic.NO);
				        list.add(article);
				        
					}
					UploadMediaResponse uploadMediaResponse = mediaAPI.uploadNews(list);
					MpNewsMsg mpNewsMsg = new MpNewsMsg();
					mpNewsMsg.setMediaId(uploadMediaResponse.getMediaId());
					MessageAPI messageAPI = new MessageAPI(config);
					GetSendMessageResponse messageResponse = messageAPI.sendMessageToUser(mpNewsMsg, true, 0);
					logger.info("Send Message Id is " + messageResponse.getMsgId());
				}
				
				Push push = pushService.getPush(Integer.valueOf(pushId));
				push.setStatus(1);
				pushService.updatePush(push);
				
			}
			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("推送失败");
		}
		return result;
	}
	
	@RequestMapping("/movie/list.jspx")
	public ModelAndView movielist(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/pushmovie/movielist");
		return mv;
	}
	
	@RequestMapping("/movie/page.jspx")
	public @ResponseBody
	DataTableDataGrid moviepage(HttpServletRequest request) {

		Pagination page = RequestUtils.getDataTablePagination(request);
		String draw = request.getParameter("draw");
		if (draw == null || draw.trim().length() == 0) {
			draw = "1";
		}

		DataMap map = new DataMap();

		String name = request.getParameter("name");
		String push_id = request.getParameter("push_id");

		if (StringUtils.isNotEmpty(name)) {
			map.put("name", "%" + name +"%");
		}
		if (StringUtils.isNotEmpty(push_id)) {
			map.put("type", push_id);
		}

		page = pushmovieService.selectPageList(map, page);

		DataTableDataGrid grid = new DataTableDataGrid(Integer.parseInt(draw), page,
				new String[] { "pushmovie_id","push_id", "film_id", "name"});

		return grid;
	}
	
	@RequestMapping("/movie/add.jspx")
	public ModelAndView movieadd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/pushmovie/addmovie");
		String type = request.getParameter("push_id");
		if (StringUtils.isNotEmpty(type)) {
			DataMap mmap = new DataMap();
			mmap.put("push_id", type);
			List<PushMovie> list = pushmovieService.selectList(mmap);
			if(CollectionUtils.isNotEmpty(list)){
				StringBuffer sb = new StringBuffer();	
				for(PushMovie m:list){
					sb.append(m.getFilmId()).append(",");
				}
				mv.addObject("notin", sb.deleteCharAt(sb.length() - 1).toString());
			}
		}
		
		return mv;
	}
	
	@RequestMapping("/movie/add-save.jspx")
	public @ResponseBody 
	JSONResult movieaddsave(HttpServletRequest request,PushMovie movie) {
		JSONResult result = new JSONResult();
		try {
			pushmovieService.saveMovie(movie);
			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("添加失败");
		}
		return result;
	}
	
	@RequestMapping("/movie/delete.jspx")
	public @ResponseBody 
	JSONResult moviedelete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String movieId = request.getParameter("pushmovieId");

			if (StringUtils.isNotEmpty(movieId)) {
				pushmovieService.deleteMovie(Integer.parseInt(movieId));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
}
