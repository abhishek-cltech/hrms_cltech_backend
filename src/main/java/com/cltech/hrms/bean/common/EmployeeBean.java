package com.cltech.hrms.bean.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties
public class EmployeeBean implements Serializable {
	private Long id;
	private double totalExperience;
	private String totalSkill;
	private String lookingFor;
	private String name;
	private String email;
	private String phone;
	private String departmentName;
	private String resumeStatusCode;
	private String resumeStatusValue;
	
	public EmployeeBean() {
		super();
	}

	public EmployeeBean(Long id, double totalExperience, String totalSkill, String lookingFor, String name,
			String email, String phone, String departmentName, String resumeStatusCode, String resumeStatusValue) {
		super();
		this.id = id;
		this.totalExperience = totalExperience;
		this.totalSkill = totalSkill;
		this.lookingFor = lookingFor;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.departmentName = departmentName;
		this.resumeStatusCode = resumeStatusCode;
		this.resumeStatusValue = resumeStatusValue;
	}

	



	


	
	
//	@JsonProperty
//	private boolean isFresher;
//	@JsonProperty
//	private boolean isWorking;

	
}
