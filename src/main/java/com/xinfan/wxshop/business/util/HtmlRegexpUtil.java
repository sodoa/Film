package com.xinfan.wxshop.business.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * 
 */
public class HtmlRegexpUtil {

	private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
	private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签
	private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

	public static String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	/**
	 * 
	 * 基本功能：判断标记是否存在
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String summary(String input, int length, String nexfix) {

		String text = filterHtml(input);

		if (text == null) {
			return "";
		}

		if (text.length() <= length) {
			return text;
		}

		return text.substring(0, length) + nexfix;
	}
}
