package com.cltech.hrms.bean.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @CreatedBy
	@Column(name="created_by", updatable=false)
	private String createdBy;
	
	@CreatedDate
	@Column(name="created_dt", updatable =false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDt;
	
	@LastModifiedBy
	@Column(name="modified_by")
	private String modifiedBy;
	
	@LastModifiedDate
	@Column(name="modified_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDt;
	
	@Column(name="isActive")
    private Boolean active;
    
}
