package com.xinfan.wxshop.common.base;

public class BizException extends RuntimeException {
	
	private int code;
	 
	private String message;
	
	private String view;
	
	
	
	private Exception exception;
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public BizException(int code){
		this.code = code;
	}
	
	public BizException(int code,String msg){
		this.code = code;
		this.message = msg;
	}
	
	public BizException(String msg){
		this.message = msg;
	}
	
	public BizException(String msg, Exception e) {
		this.message = msg;
		this.exception = e;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	
	
}
