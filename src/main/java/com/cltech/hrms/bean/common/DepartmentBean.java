package com.cltech.hrms.bean.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties
public class DepartmentBean {
	@JsonProperty("departmentId")
	private long departmentId;
	
	@JsonProperty("departmentName")
	private String departmentName;

	public DepartmentBean(long departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	
	
}
