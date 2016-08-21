package com.xinfan.wxshop.business.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.business.entity.ShareImage;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.ShareService;
import com.xinfan.wxshop.common.base.DataMap;

@Controller
@RequestMapping("/admin/share")
public class ShareAction {

	private static final Logger logger = LoggerFactory.getLogger(ShareAction.class);

	@Autowired
	private ShareService ShareService;

	@RequestMapping("/list.jspx")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/list");
		List list = ShareService.listShare()	;
		mv.addObject("list", list);
		return mv;
	}
	
	
	@RequestMapping("/update.jspx")
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/update");
		
		String shareid = request.getParameter("shareid");
		Share share = this.ShareService.getShare(Integer.parseInt(shareid));
		mv.addObject("share", share);
		
		return mv;
	}

	@RequestMapping("/save-update.jspx")
	public @ResponseBody
	JSONResult saveUpdate(HttpServletRequest request, Share pojo) {
		JSONResult result = new JSONResult();
		try {

			ShareService.updateShare(pojo);

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("更新失败");
		}
		return result;
	}

	

	@RequestMapping("/add.jspx")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/add");
		String type = request.getParameter("type");
		if (StringUtils.isNotEmpty(type)) {
			DataMap mmap = new DataMap();
			mmap.put("type", type);
		}

		return mv;
	}

	@RequestMapping("/add-save.jspx")
	public @ResponseBody
	JSONResult addsave(HttpServletRequest request, Share pojo) {
		JSONResult result = new JSONResult();
		try {

			ShareService.insertShare(pojo);

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("添加失败");
		}
		return result;
	}

	@RequestMapping("/delete.jspx")
	public @ResponseBody
	JSONResult delete(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String id = request.getParameter("id");

			if (StringUtils.isNotEmpty(id)) {
				ShareService.deleteShare(Integer.parseInt(id));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
	
	
	@RequestMapping("/image-list.jspx")
	public ModelAndView imageList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/imagelist");
		return mv;
	}
	
	@RequestMapping("/delete-image.jspx")
	public @ResponseBody
	JSONResult deleteImage(HttpServletRequest request) {

		JSONResult result = new JSONResult();

		try {

			String id = request.getParameter("id");

			if (StringUtils.isNotEmpty(id)) {
				ShareService.deleteShareImage(Integer.parseInt(id));
			}

			result = result.success();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = result.error("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/add-image-list.jspx")
	public ModelAndView addImageList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/addimagelist");
		return mv;
	}
	
	@RequestMapping("/save-image-list.jspx")
	public ModelAndView save(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/addimagelist");
		
		
		String shareid = request.getParameter("shareid");
		
		ShareImage pojo = new ShareImage();
		pojo.setShareid(Integer.parseInt(shareid));
		
		ShareService.insertShareImage(pojo);
		
		return mv;
	}
	
}
