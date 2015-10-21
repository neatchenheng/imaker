package com.i9144.xpage.exception;

public class HelperException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private Object[] params;
	private String message;
	
	public HelperException(String message) {
		this.code = -1;
		this.params = null;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
