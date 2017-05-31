package com.safty.domain.response;

import java.io.Serializable;

public class RiskResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1922263451002591535L;
	
	private String code;
	
	private String message;

	public RiskResponse() {
		super();
	}

	public RiskResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	
	
	

}
