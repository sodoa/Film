package com.xinfan.wxshop.business.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {

	public static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void run(Thread command){
		exec.execute(command);
	}
}
