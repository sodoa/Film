package com.xinfan.wxshop.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class  OrderCreaterUtils {

	public static String createOrderNo(String customerId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

		int random = new Random().nextInt(9999);

		String no = customerId + sdf.format(new Date()) + random;

		return no;
	}

}
