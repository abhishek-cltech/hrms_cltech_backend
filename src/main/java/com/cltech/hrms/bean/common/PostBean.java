package com.cltech.hrms.bean.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties
public class PostBean {
	private long id;
	private long departmentId;
	private String departmentName;
	public PostBean(long id, long departmentId, String departmentName) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public PostBean() {
		super();
	}
	
	
}
