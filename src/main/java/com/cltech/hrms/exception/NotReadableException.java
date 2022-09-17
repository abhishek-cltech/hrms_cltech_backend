package com.cltech.hrms.exception;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import lombok.CustomLog;


public class NotReadableException extends HttpMessageNotReadableException {

	public NotReadableException(String msg, Throwable cause, HttpInputMessage httpInputMessage) {
		super(msg, cause, httpInputMessage);
		// TODO Auto-generated constructor stub
		
		
	}

}
