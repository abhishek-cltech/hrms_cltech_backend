package com.cltech.hrms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
public class EmployeeDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="employeeDetailId")
	private List<Address> addreses;

	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String alternateEmail;
	
	@Column
	private String phone;
	
	@Column
	private String alternatePhone;
	
	@Column
	private String gender;
	
	@Column
	private String dob;

}
