/**
 * 
 */
package com.xinfan.wxshop.business.util;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.time.DateUtils;

import com.xinfan.wxshop.common.base.DataMap;
import com.xinfan.wxshop.common.util.LogUtils;

/**
 * @author Administrator
 * 
 */
public class ParamterUtils {

	public static String stringFilter(String str) {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	

	public static void addQueryLoginTime(DataMap map) {
		String time = map.getString("logintime");
		if (time != null && time.trim().length() > 0) {
			try {
				Date date = DateUtils.parseDate(time,
						new String[] { "yyyy-MM-dd" });
				map.put("logintime", date.getTime());
				return;
			} catch (ParseException e) {
				LogUtils.error(e.getMessage(), e);
			}
		}
		map.remove("logintime");
	}

	//
	public static void addQueryCreatetime(DataMap map) {
		String time = map.getString("createtime");
		if (time != null && time.trim().length() > 0) {
			try {
				Date date = DateUtils.parseDate(time,
						new String[] { "yyyy-MM-dd" });
				map.put("createtime", date.getTime());
				return;
			} catch (ParseException e) {
				LogUtils.error(e.getMessage(), e);
			}
		}
		map.remove("createtime");
	}

	public static void addQueryTime(DataMap map, String name) {
		String time = map.getString(name);
		if (time != null && time.trim().length() > 0) {
			try {
				Date date = DateUtils.parseDate(time,
						new String[] { "yyyy-MM-dd" });
				map.put(name, date.getTime());
				return;
			} catch (ParseException e) {
				LogUtils.error(e.getMessage(), e);
			}
		}
		map.remove(name);
	}

	public static void addIntervalQueryTime(DataMap map) {
		String starttime = map.getString("STARTTIME");
		if (starttime != null && starttime.trim().length() > 0) {
			try {
				Date date = DateUtils.parseDate(starttime,
						new String[] { "yyyy-MM-dd" });
				map.put("STARTTIME", date);
				return;
			} catch (ParseException e) {
				LogUtils.error(e.getMessage(), e);
				map.remove("STARTTIME");
			}
		}

		String endtime = map.getString("ENDTIME");
		if (endtime != null && endtime.trim().length() > 0) {
			try {
				Date date = DateUtils.parseDate(endtime,
						new String[] { "yyyy-MM-dd" });
				date = DateUtils.addHours(date, 23);
				date = DateUtils.addMinutes(date, 59);
				date = DateUtils.addSeconds(date, 59);
				map.put("ENDTIME", date);
				return;
			} catch (ParseException e) {
				LogUtils.error(e.getMessage(), e);
				map.remove("ENDTIME");
			}
		}
	}

	public static void addManager(DataMap map) {
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(
				"sessionMap");
		String userid = sessionMap.getString("userId");
		map.put("manager", userid);
	}

	public static String getManager() {
		DataMap sessionMap = (DataMap) RequestUtils.getSession().getAttribute(
				"sessionMap");
		String userid = sessionMap.getString("userId");
		return userid;
	}
}
