package com.cltech.hrms.bean.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GridDatatableRequestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Paging
	private int pagenum;
	private int pagesize;
	private int recordendindex;
	private int recordstartindex;
	private int totalRecords;
	
	//sorting
	private String sortdatafield;
	private String sortorder;
		
	//Filter
	private List<FilterGroupsBean> filterGroups;
	
	//Grouping
	private int groupscount;
	//TODO: Need to implement grouping 
	
	//Extra fields
	private Map<String, Object> extraParam;
	
	
}
