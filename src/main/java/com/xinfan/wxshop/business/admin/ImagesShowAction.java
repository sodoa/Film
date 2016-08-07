package com.xinfan.wxshop.business.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinfan.wxshop.business.util.FilePathHelper;

@Controller
@RequestMapping("/")
public class ImagesShowAction {

	private static final Logger logger = LoggerFactory.getLogger(ImagesShowAction.class);

	private static final String GIF = "image/gif;charset=UTF-8";
	private static final String JPG = "image/jpeg;charset=UTF-8";

	@RequestMapping(value = "/image.jspx")
	public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String imagePath = request.getParameter("i");
		if (imagePath == null || imagePath.trim().length() == 0) {
			imagePath = "/resource/img/no.jpg";
		}
		
		imagePath = imagePath.replace("\\", "/");
		
		String rootPath = FilePathHelper.getFileStoreMainPath()+"/" + imagePath;
		
		OutputStream output = response.getOutputStream();// 得到输出流
		try {
			if (imagePath.toLowerCase().endsWith(".jpg"))// 使用编码处理文件流的情况：
			{
				response.setContentType(JPG);// 设定输出的类型
				// 得到图片的真实路径
				InputStream imageIn = new FileInputStream(new File(rootPath));
				BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
				BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

				byte data[] = new byte[4096];// 缓冲字节数

				int size = 0;
				size = bis.read(data);
				while (size != -1) {
					bos.write(data, 0, size);
					size = bis.read(data);
				}
				bis.close();
				bos.flush();// 清空输出缓冲流
				bos.close();
			} else if (imagePath.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
			{
				response.setContentType(GIF);
				
				InputStream imageIn = new FileInputStream(new File(rootPath));
				BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
				BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

				byte data[] = new byte[4096];// 缓冲字节数

				int size = 0;
				size = bis.read(data);
				while (size != -1) {
					bos.write(data, 0, size);
					size = bis.read(data);
				}
				bis.close();
				bos.flush();// 清空输出缓冲流
				bos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
			imagePath = "/resource/img/no.jpg";
			response.setContentType(JPG);
			
			ServletContext context = request.getServletContext();//得到背景对象   
			 
			 InputStream imageIn=context.getResourceAsStream(imagePath);

			BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
			BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

			byte data[] = new byte[4096];// 缓冲字节数

			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
			bis.close();
			bos.flush();// 清空输出缓冲流
			bos.close();
		}

	}

}
