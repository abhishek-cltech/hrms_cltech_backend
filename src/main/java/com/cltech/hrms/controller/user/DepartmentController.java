package com.cltech.hrms.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.user.Department;
import com.cltech.hrms.service.GroupMasterSerivce;
import com.cltech.hrms.service.user.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	
	@Autowired
	@Qualifier("departmentServiceImpl")
	DepartmentService departmentService;
	
	@Autowired
	@Qualifier("groupMasterSerivceImpl")
	GroupMasterSerivce groupMasterService;

	
	@PostMapping("/saveDepartment")
	public ResponseBean addDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/getAllDepartments")
	public ResponseBean getAllDepartment() {
		return departmentService.getAllDepartment();
	}

	@GetMapping("/getDepartmentById/{ID}")
	public ResponseBean getDepartmentById(@PathVariable("ID") Long id) {
		return departmentService.getDepartmentById(id);
	}

	@PostMapping("/updateDepartment")
	public ResponseBean updateDepartment(@RequestBody Department department) {
		return departmentService.updateDepartment(department);
	}
	
	@PostMapping("/getAllDepartmentGrid")
	public ResponseBean updateDepartment(@RequestBody DataTableRequestBean dataTableRequestBean) {
		return departmentService.getAllDepartmentGrid(dataTableRequestBean);
	}
	
	//group master
	    @PostMapping("/saveGroupMaster")
		public ResponseBean addGroupMaster(@RequestBody GroupMaster groupMaster) {
			return groupMasterService.saveGroupMaster(groupMaster);
		}

		@GetMapping("/getGroupMasterByGroupName")
		public ResponseBean getAllGroupMaster() {
			return groupMasterService.getGroupMasterByGroupName();
		}

		@GetMapping("/getGroupMasterById/{ID}")
		public ResponseBean getGroupMasterById(@PathVariable("ID") Long id) {
			return groupMasterService.getGroupMasterById(id);
		}

		@PostMapping("/updateGroupMaster")
		public ResponseBean updateGroupMaster(@RequestBody GroupMaster groupMaster) {
			return groupMasterService.updateGroupMaster(groupMaster);
		}
		
		@PostMapping("/getAllGroupMasterGrid")
		public ResponseBean updateGroupMaster(@RequestBody DataTableRequestBean dataTableRequestBean) {
			return groupMasterService.getAllGroupMasterGrid(dataTableRequestBean);
		}
		

}
