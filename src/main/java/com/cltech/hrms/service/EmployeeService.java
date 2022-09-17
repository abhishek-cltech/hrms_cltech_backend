package com.cltech.hrms.service;

import java.util.List;

import com.cltech.hrms.bean.Employee;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.GridDatatableRequestBean;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

	public ResponseBean saveEmployee(Employee employee);

	public ResponseBean updateEmployee(Employee employee);

	public ResponseBean getAllEmployee( GridDatatableRequestBean gridDatatableRequestBean);

	public ResponseBean getEmployeeById(Long id);

	public ResponseBean getAllEmployees(DataTableRequestBean dataTableRequestBean);

	public ResponseBean getAllEmployeesForAdmin(DataTableRequestBean dataTableRequestBean);

	public ResponseBean updateEmployeeResumeStatus(Employee employee);

	ResponseBean getAllSelectedApplicantReport(DataTableRequestBean dataTableRequestBean);
	public ResponseBean saveBulkEmployee(List<Employee> employees);
	
	public ResponseBean getApplicant(Employee employee);

	public ResponseBean uploadEmployeeExcel(MultipartFile file);

}
