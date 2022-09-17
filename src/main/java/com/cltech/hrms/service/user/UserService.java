package com.cltech.hrms.service.user;

import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.user.User;

@Service
public interface UserService {
	public ResponseBean  registration(User user);
	public ResponseBean  findByUsername(String username);
	public ResponseBean  loginAuthentication(User userForm);
	public ResponseBean  getAllUser(DataTableRequestBean dataTableRequestBean);
	public ResponseBean  findById(long id);
	public ResponseBean  updateUserDepartment(User user);
	public ResponseBean getAllRegisterUser(DataTableRequestBean dataTableRequestBean);
	public ResponseBean updateProfile(User user);
	public ResponseBean resetPassword(User user);
	public ResponseBean getDashBoardData();
	public ResponseBean getUser(User user);
	public ResponseBean removeDeptfromUser(Long id);
}
