package com.cltech.hrms.exception;

import org.springframework.dao.DataIntegrityViolationException;

@SuppressWarnings("serial")
public class BadRequest extends DataIntegrityViolationException {

	private String msg;

	public BadRequest(String msg) {
		super(msg);
	}

}
