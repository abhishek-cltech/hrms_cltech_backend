package com.cltech.hrms.bean.common;

import java.util.List;

import com.cltech.hrms.utility.dataTable.Column;
import com.cltech.hrms.utility.dataTable.Order;
import com.cltech.hrms.utility.dataTable.Search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataTableRequestBean {
	
private int draw;
private List<Column> columns;
private List<Order> order;
private int start;
private int length; 
private Search search; 
private String searchText;
private String sortableColumn;
private String extraParam;

}
