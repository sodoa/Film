package com.xinfan.wxshop.business.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xinfan.wxshop.business.helper.FilePathHelper;
import com.xinfan.wxshop.common.util.JSONUtils;

@Controller
@RequestMapping("/admin/images/")
public class ImageUploadAction {

	private static final Logger logger = LoggerFactory.getLogger(ImageUploadAction.class);

	@RequestMapping(value = "/upload_images.jspx")
	public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Map result = new HashMap();

		System.out.println("开始");
		String path = FilePathHelper.getImageListUploadPath(request);
		// String fileName = file.getOriginalFilename();
		String fileName = new Date().getTime() + ".jpg";

		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			result.put("code", 200);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put("code", 500);
		}

		try {
			response.getWriter().println(JSONUtils.toJSONString(result));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping(value = "/upload_thumd.jspx")
	public void uploadGoodsThumd(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		
		Map result = new HashMap();

		System.out.println("开始");
		String path = FilePathHelper.getImageThumdUploadPath(request);
		// String fileName = file.getOriginalFilename();
		String fileName = new Date().getTime() + ".jpg";

		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			result.put("code", 200);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put("code", 500);
		}

		try {
			response.getWriter().println(JSONUtils.toJSONString(result));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
	}

}
