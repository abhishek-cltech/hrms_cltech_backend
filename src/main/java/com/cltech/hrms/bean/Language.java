package com.cltech.hrms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Language implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String languageName;

	private String proficient;

	@JsonProperty
	private boolean isRead;

	@JsonProperty
	private boolean isSpeak;
    
	@JsonProperty
	private boolean isWrite;
	
//	@ManyToOne(targetEntity = Employee.class,optional=false,cascade = CascadeType.ALL)
//	@JoinColumn(name="employee_id")
//	private Employee employee;
}
