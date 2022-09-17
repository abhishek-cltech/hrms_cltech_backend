package com.cltech.hrms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String schoolName;
	
	@Column
	private String degree;
	
	@Column
	private String fieldOfStudy;
	
	
	@Column
	private String location;
	
	
	@Column
	private String completionDate;
	
	@Column
	private Double passingPercentage;
	
//	@ManyToOne
//	@JoinColumn(name="employee_id")
//	private Employee employee;

   

}
