package com.auth.security;

import com.core.exception.MicroException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = OAuth2ExceptionJacksonSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
	private String code;
	private String message;
	private String exception;
 

	public CustomOAuth2Exception(MicroException e) {
		super(null);
		code = e.getErrorCode();
		message = e.getMessage();
		exception = e.getClass().getName();
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


	public String getException() {
		return exception;
	}


	public void setException(String exception) {
		this.exception = exception;
	}


	@Override
	public String toString() {
		return "CustomOAuth2Exception [code=" + code + ", message=" + message + ", exception=" + exception + "]";
	}

}
