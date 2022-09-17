package com.cltech.hrms.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.user.User;
import com.cltech.hrms.service.GroupMasterSerivce;
import com.cltech.hrms.service.user.RoleService;
import com.cltech.hrms.service.user.UserService;


@RestController
@CrossOrigin( origins= {"*"},allowCredentials = "false",maxAge = 4800)
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("roleServiceImp")
	private RoleService roleService;
	
	@Autowired
	private GroupMasterSerivce groupMasterService;
		
	@PostMapping("/registration")
	@ResponseBody
    public ResponseBean registration(@RequestBody User user) {
        return userService.registration(user);
    }

	@PostMapping("/login")
    @ResponseBody
    public ResponseBean login(@RequestBody User user) {
        return userService.loginAuthentication(user);
    }
	
	@PostMapping("/getAllUser")
	public ResponseBean getAllUser(@RequestBody DataTableRequestBean dataTableRequestBean) {
		return userService.getAllUser  (dataTableRequestBean);
		
	}
	
	@PostMapping("/updateUserDepartment")
	@ResponseBody
    public ResponseBean updateUserDepartment(@RequestBody User user) {
        return userService.updateUserDepartment(user);
    }
	
	@GetMapping(path="/findUseById/{ID}")
	@ResponseBody
    public ResponseBean findUserById(@PathVariable("ID") Long id) {
        return userService.findById(id);
    }

	@PostMapping("/getRole")
	public ResponseBean getRole() {
		return roleService.getRole  ();
		
	}
	@PostMapping("/getSecurityQuestions")
	public ResponseBean getSecurityQuetions() {
		return groupMasterService.getSecurityQuestionGroup();
	}
	
	
	@GetMapping("/findByUsername")
	@ResponseBody
    public ResponseBean findByUsername(@RequestParam String email) {
        return userService.findByUsername(email);
    }
	
	@PostMapping("/isUserValid")
	public ResponseBean isUserValid(@RequestBody User user) {
		return userService.getUser(user);
	}
	
	@PostMapping("/getAllRegisterUser")
	public ResponseBean getAllRegisterUser(@RequestBody DataTableRequestBean dataTableRequestBean) {
		return userService.getAllRegisterUser  (dataTableRequestBean);
		
	}
	
	@PostMapping("/updateProfile")
	@ResponseBody
    public ResponseBean updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }
	
	@PostMapping("/resetPassword")
	@ResponseBody
    public ResponseBean resetPassword(@RequestBody User user) {
        return userService.resetPassword(user);
    }
	
	@PostMapping("/getDashBoardData")
	@ResponseBody
    public ResponseBean getDashBoardData() {
        return userService.getDashBoardData();
    }
	

	@GetMapping("/removeDepartmentByUserId/{ID}")
	public ResponseBean removeDepartmentByUserId(@PathVariable("ID") Long id) {
		return userService.removeDeptfromUser(id);
	}
}
