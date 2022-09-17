package com.cltech.hrms.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cltech.hrms.bean.common.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Employee extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String carrierObjective; // allowed character 256 or 512

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employeeDetailId")
	private EmployeeDetail employeeDetail;

	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="employeeId")
	private List<Skill> skills;

	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="employeeId")
	private List<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="employeeId")
	private List<Education> educations;

	
	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="employeeId")
	private List<Project> projects;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="employeeId")
	private List<Language> languages;

	@Column
	@JsonProperty
	private boolean isWorking;
	
	@Column
	@JsonProperty
	private boolean isFresher;
	
	private double currentCTC;
	private double expectedCTC;
	private String preferedLocation; // optional
	private String certifications;
	private String hobbies;
	private String totalSkill;
	private double totalExperience;
	private String lookingFor;

	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name="socialMediaLinkId")
	private SocialMedialLinks socialMediaLinks;
	
	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name="employeeId")
	private List<Post> posts;
	
	private String resumeStatusCode;
	private String resumeStatusValue;

}
