package com.xinfan.wxshop.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	static SimpleDateFormat fullsdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static String formatFull(Date date ){
		return fullsdf.format(date);
	}
	
}
