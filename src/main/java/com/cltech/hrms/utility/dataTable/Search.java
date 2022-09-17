package com.cltech.hrms.utility.dataTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {
	private String value;
	private boolean regex;

}
