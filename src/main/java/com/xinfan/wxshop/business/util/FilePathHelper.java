package com.xinfan.wxshop.business.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinfan.wxshop.common.config.FileConfig;

public class FilePathHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(FilePathHelper.class);

	public static String getImageListUploadPath(HttpServletRequest request) {
		String time = request.getParameter("time");
		String path = request.getSession().getServletContext().getRealPath("temp");
		path = path.replace("\\", "/");
		path = path + "/g/" + time + "/lt";
		return path;
	}

	public static String getImageThumdUploadPath(HttpServletRequest request) {
		String time = request.getParameter("time");
		String path = request.getSession().getServletContext().getRealPath("temp");
		path = path.replace("\\", "/");
		path = path + "/g/" + time + "/tb";
		return path;
	}

	public static String getImageListToSavePath(String realdir) {
		String path = "/file/g/" + realdir + "/lt";
		return path;
	}
	
	public static String getGoodsImageListToSavePath(String realdir) {
		String path = "/file/g/" + realdir + "/s";
		return path;
	}

	public static String getGoodsFashionImageListToSavePath(String realdir) {
		String path = "/file/g/" + realdir + "/f";
		return path;
	}


	public static String getImageThumdToSavePath(String realdir) {
		String path = "/file/g/" + realdir + "/tb/";
		return path;
	}
	
	
	public static String getGoodsSummaryHtmlPath() {
		String realdir = String.valueOf(new Date().getTime());
		String path = "/file/static/g/" + realdir +".html";
		return path;
	}
	
	public static String getRealPath(HttpServletRequest request) {
		String basepath = request.getSession().getServletContext().getRealPath("/");
		basepath = basepath.replace("\\", "/");
		return basepath;
	}
	
	public static String getGoodsSummaryHtml(HttpServletRequest request,String realdir) {
		String basepath = request.getSession().getServletContext().getRealPath("/");
		basepath = basepath.replace("\\", "/");

		File file = new File(basepath + realdir);
		if(file.exists()){
			String html = "";
			try {
				html = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return html;
		}
		return "";
	}
	
	public static String getArticleContentHtml(HttpServletRequest request,String realdir) {
		String basepath = request.getSession().getServletContext().getRealPath("/");
		basepath = basepath.replace("\\", "/");

		File file = new File(basepath + realdir);
		if(file.exists()){
			String html = "";
			try {
				html = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return html;
		}
		return "";
	}
	
	public static String getArticleContentHtmlToSavePath() {
		String realdir = String.valueOf(new Date().getTime());
		String path = "/file/article/content/"+realdir+".html";
		return path;
	}

	public static String getArticleImgToSavePath() {
		String realdir = String.valueOf(new Date().getTime());
		String path = "/file/article/image/" + realdir +".jpg";
		return path;
	}
	
	public static String getFileStoreMainPath(){
		return FileConfig.getInstance().getString("file.store.path", "/opt/store");
		//return FileConfig.getInstance().getString("file.store.path", "c://");
	}
	
	public static String getFileStoreMainPathWithFilmHeader(){
		//return FileConfig.getInstance().getString("file.store.path", "/opt/store");
		return "/header"  ;
	}
	
	public static String getImageUploadPath(String type,HttpServletRequest request) {
		String time = request.getParameter("time");
		String path = request.getSession().getServletContext().getRealPath("temp");
		path = path.replace("\\", "/");
		path = path + "/g/" + time + "/lt";
		return path;
	}
	
}
