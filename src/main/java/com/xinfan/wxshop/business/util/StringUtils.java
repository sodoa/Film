package com.xinfan.wxshop.business.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static String stringFilter(String str)  {
		if(str == null){
			return null;
		}
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
