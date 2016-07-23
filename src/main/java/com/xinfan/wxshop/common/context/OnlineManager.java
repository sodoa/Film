package com.xinfan.wxshop.common.context;

/**
 * @author huangmin
 * @DATE 2016年7月23日下午10:11:29
 * 
 */
public class OnlineManager {

	private static OnlineManager om;

	private int count;
	
	private OnlineManager(){
		count = 0;
	 }

	public static OnlineManager getInstance() {
		if (om == null) {
			om = new OnlineManager();
		}
		return om;
	}
	
	public int add(){
		count = count +1 ;
		return count;
	}
	
	public int remove(){
		count = count - 1;
		return count;
	}
	
	public int get(){
		return count;
	}
}
