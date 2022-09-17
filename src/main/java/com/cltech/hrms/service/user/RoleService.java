package com.cltech.hrms.service.user;

import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.user.User;

@Service
public interface RoleService {
	public ResponseBean  getRole();
	
}
