package com.xinfan.wxshop.business.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xinfan.wxshop.business.entity.Share;
import com.xinfan.wxshop.business.entity.ShareImage;
import com.xinfan.wxshop.business.model.JSONResult;
import com.xinfan.wxshop.business.service.ShareService;
import com.xinfan.wxshop.business.util.FilePathHelper;
import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.util.JSONUtils;

@Controller
@RequestMapping("/admin/share")
public class ShareAction {

	private static final Logger logger = LoggerFactory.getLogger(ShareAction.class);

	@Autowired
	private ShareService ShareService;

	@RequestMapping("/list.jspx")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/list");
		List list = ShareService.listShare();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/update.jspx")
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/update");

		String shareid = request.getParameter("id");
		Share share = this.ShareService.getShare(Integer.parseInt(shareid));
		mv.addObject("bean", share);

		return mv;
	}

	@RequestMapping("/save-update.jspx")
	public String saveUpdate(HttpServletRequest request, Share pojo) {
		JSONResult result = new JSONResult();
		try {

			ShareService.updateShare(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "redirect:./list.jspx";
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

	@RequestMapping("/state.jspx")
	public String state(HttpServletRequest request) {

		String id = request.getParameter("id");
		String state = request.getParameter("state");

		Share updateShare = new Share();
		updateShare.setShareid(Integer.parseInt(id));
		updateShare.setOnline(Integer.parseInt(state));

		this.ShareService.updateShare(updateShare);

		return "redirect:./list.jspx";
	}

	@RequestMapping("/add-save.jspx")
	public ModelAndView addsave(HttpServletRequest request, Share pojo) {
		try {
			ShareService.insertShare(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return tip(request);
	}

	@RequestMapping("/tip.jspx")
	public ModelAndView tip(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/tip");
		String msg = request.getParameter("msg");
		mv.addObject("msg", msg);
		return mv;
	}

	@RequestMapping("/delete.jspx")
	public String delete(HttpServletRequest request) {

		try {

			String id = request.getParameter("id");

			if (StringUtils.isNotEmpty(id)) {
				ShareService.deleteShare(Integer.parseInt(id));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "redirect:./list.jspx";
	}

	@RequestMapping("/image-list.jspx")
	public ModelAndView imageList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/admin/share/imagelist");

		String id = request.getParameter("id");

		List<ShareImage> list = this.ShareService.listImageShare(Integer.parseInt(id));

		mv.addObject("list", list);
		mv.addObject("shareid", id);

		return mv;
	}

	@RequestMapping("/delete-image.jspx")
	public String deleteImage(HttpServletRequest request) {
		
		String imageid = request.getParameter("imageid");
		String shareid = request.getParameter("shareid");
		
		try {

			if (StringUtils.isNotEmpty(imageid)) {
				ShareService.deleteShareImage(Integer.parseInt(imageid));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:./image-list.jspx?id="+shareid;
	}

	@RequestMapping(value = "/save-image-list.jspx")
	public String saveImageList(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {

		System.out.println("开始");
		String basepath = FilePathHelper.getFileStoreMainPath();
		// String fileName = file.getOriginalFilename();
		String fileName = new Date().getTime() + ".jpg";
		String relativePath = "/share/" + fileName;

		// 保存
		try {
			FileUtils.writeByteArrayToFile(new File(basepath + relativePath), file.getBytes());
			String shareid = request.getParameter("shareid");

			ShareImage pojo = new ShareImage();
			pojo.setShareid(Integer.parseInt(shareid));
			pojo.setImageurl(relativePath);

			ShareService.insertShareImage(pojo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		String shareid = request.getParameter("shareid");

		return "redirect:./image-list.jspx?id="+shareid;
	}

}
