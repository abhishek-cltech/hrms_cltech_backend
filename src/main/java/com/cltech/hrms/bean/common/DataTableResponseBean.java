package com.cltech.hrms.bean.common;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataTableResponseBean<T> {
	
	private List<T> data ;
    private int draw ;
    private long recordsFiltered ;
    private long recordsTotal ;
	
}
