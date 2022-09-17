package com.cltech.hrms.bean;

import com.cltech.hrms.constant.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseBean {
	private Status status;
	private String message;
	private Object response;

}
