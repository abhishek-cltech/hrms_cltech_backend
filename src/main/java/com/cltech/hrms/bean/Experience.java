package com.cltech.hrms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Experience implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String jobTitle;
	
	@Column
	private String jobDescription;
	
	@Column
	private String organizationName;
	
	@Column
	private String startDate;
	
	@Column
	private String endDate;
	
	@Column
	@JsonProperty
	private boolean isCurrentlyWorking;
	
//	@ManyToOne
//	@JoinColumn(name="employee_id" )
//	private Employee employee;
}
