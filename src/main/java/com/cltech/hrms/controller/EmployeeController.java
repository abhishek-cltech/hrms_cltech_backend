package com.cltech.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cltech.hrms.bean.Employee;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.GridDatatableRequestBean;
import com.cltech.hrms.service.impl.EmployeeServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping("/test")
	public String testApi(){
		return "Api Test Success..";
	}
	@PostMapping(path = "saveEmployee")
	@ResponseBody
	public ResponseBean saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
		
	}
	@PostMapping(path = "updateEmployee")
	@ResponseBody
	public ResponseBean updateEmployee(@RequestBody Employee employee) {
	     return employeeService.updateEmployee(employee);
	}

	@PostMapping(path = "getAllEmployee")
	public ResponseBean getAllEmployee(@RequestBody GridDatatableRequestBean gridDatatableRequestBean) {
		return employeeService.getAllEmployee(gridDatatableRequestBean);
	}
	
	@RequestMapping(value="getAllEmployees" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getAllEmployees(@RequestBody DataTableRequestBean dataTableRequestBean)  {
		return employeeService.getAllEmployees(dataTableRequestBean);
	}

	@RequestMapping(path ="/getEmployeeById/{ID}" )
	public ResponseBean getEmployeeById(@PathVariable("ID") Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PostMapping(path = "getAllEmployeesForAdmin")
	public ResponseBean getAllEmployeesForAdmin(@RequestBody DataTableRequestBean dataTableRequestBean) {
		return employeeService.getAllEmployeesForAdmin(dataTableRequestBean);
	}
	
	@PostMapping(path = "/updateEmployeeResumeStatus")
	public ResponseBean updateEmployeeResumeStatus(@RequestBody Employee employee) {
		return employeeService.updateEmployeeResumeStatus(employee);
	}
	
	@PostMapping(path = "/getAllSelectedApplicantReport")
	public ResponseBean getAllSelectedApplicantReport(@RequestBody DataTableRequestBean dataTableRequestBean) {
		return employeeService.getAllSelectedApplicantReport(dataTableRequestBean);
	}
	
	@PostMapping(path = "getApplicant")
	@ResponseBody
	public ResponseBean getApplicant(@RequestBody Employee employee) {
		return employeeService.getApplicant(employee);
	}

	@RequestMapping(value="uploadEmployeeExcel" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean uploadEmployeeExcel(@RequestParam("file") MultipartFile file) {
		return employeeService.uploadEmployeeExcel(file);
	}
	
}
