package com.cltech.hrms.service.user;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.user.Department;

public interface DepartmentService {
	
	ResponseBean saveDepartment(Department employee);
	ResponseBean updateDepartment(Department employee);
	ResponseBean getAllDepartmentGrid(DataTableRequestBean dataTableRequestBean);
	ResponseBean getDepartmentById(Long id);
	ResponseBean getAllDepartment();
	

}
