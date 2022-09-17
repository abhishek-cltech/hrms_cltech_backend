package com.cltech.hrms.bean.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.bean.common.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor

public class User extends AbstractAuditingEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	 private String firstName;
	 private String lastName;
	 
     private String email;

     private String password;

    @Transient
    private String confirmPassword;

    private String role;
    private Long roleId;
    
    private String  deparmentIds;
    
    private String  departments;
    
    @Transient
    private List<Department> department;
    
    private String questionCode;
    
    private String questionAnswer;
    
    
    private String  returnUrl;
    
   

}
