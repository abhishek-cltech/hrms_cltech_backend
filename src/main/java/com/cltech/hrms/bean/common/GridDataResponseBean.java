package com.cltech.hrms.bean.common;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GridDataResponseBean<T> implements Serializable{
	private int totalRecords;
	private List<T> rows;
}
