package com.xinfan.wxshop.business.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xinfan.wxshop.business.helper.FilePathHelper;
import com.xinfan.wxshop.business.pay.weixin.CommonUtil;
import com.xinfan.wxshop.common.util.JSONUtils;

public class WxQrcodeUtils {

	private static final Logger logger = LoggerFactory.getLogger(WxQrcodeUtils.class);

	public static final String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

	public static final String show_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

	public static byte[] getQrcode(HttpServletRequest request, int id) {

		byte[] images = getFileQrcode(request, id);
		if (images != null) {
			return images;
		}

		String fullUrl = url + WeiXinSessionManager.getAccessToken();

		Map inputMap = new HashMap();
		Map sceneMap = new HashMap();
		Map sceneStrMap = new HashMap();
		sceneStrMap.put("scene_str", "" + id);
		sceneMap.put("scene", sceneStrMap);
		inputMap.put("action_info", sceneMap);
		inputMap.put("action_name", "QR_LIMIT_STR_SCENE");

		String json = JSONUtils.toJSONString(inputMap);

		logger.debug(json);

		JSONObject result = CommonUtil.httpsRequest(fullUrl, "POST", json);
		if (result != null) {
			logger.debug(result.toJSONString());
			String ticket = result.getString("ticket");

			String urlString = show_url + ticket;

			String filename = "/file/qrcode/" + id + ".jpg";

			String savePath = FilePathHelper.getRealPath(request);

			return download(urlString, filename, savePath);
		}

		return null;
	}

	public static byte[] getFileQrcode(HttpServletRequest request, int id) {
		String filename = "/file/qrcode/" + id + ".jpg";
		String savePath = FilePathHelper.getRealPath(request);

		File file = new File(savePath + filename);
		if (file.exists()) {
			try {
				return FileUtils.readFileToByteArray(file);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return null;

	}

	public static byte[] download(String urlString, String filename, String savePath) {

		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath + filename).getParentFile();
			if (!sf.exists()) {
				sf.mkdirs();
			}
			  
			File file  =new File(savePath + filename);
			if(file.exists()){
				file.delete();
			}
			File file2  =new File(savePath + filename);
			file2.createNewFile();
			
			OutputStream os = new FileOutputStream(file2);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();

			return FileUtils.readFileToByteArray(new File(savePath + filename));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;

	}
}
