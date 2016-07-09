package com.xinfan.wxshop.business.util;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.xinfan.wxshop.common.base.DataMap;

public class QueryParamterUtils {

	public static void addQueryTime(DataMap map,String startTimeName,String endTimeName) {
		try {
			String startTime = map.getString(startTimeName);
			String endTime = map.getString(endTimeName);

			if (startTime != null && startTime.trim().length() > 0) {
				Date formatStartTime = DateUtils.parseDate(startTime, new String[] { "yyyy-MM-dd" });
				formatStartTime = DateUtils.addHours(formatStartTime, 0);
				formatStartTime = DateUtils.addMinutes(formatStartTime, 0);
				formatStartTime = DateUtils.addSeconds(formatStartTime, 0);
				map.put(startTimeName, formatStartTime);
			}

			if (endTime != null && endTime.trim().length() > 0) {
				Date formatEndTime = DateUtils.parseDate(endTime, new String[] { "yyyy-MM-dd" });
				formatEndTime = DateUtils.addHours(formatEndTime, 23);
				formatEndTime = DateUtils.addMinutes(formatEndTime, 59);
				formatEndTime = DateUtils.addSeconds(formatEndTime, 59);
				map.put(endTimeName, formatEndTime);
			}

		} catch (Exception e) {
			map.remove(endTimeName);
			map.remove(startTimeName);
		}
	}
}
