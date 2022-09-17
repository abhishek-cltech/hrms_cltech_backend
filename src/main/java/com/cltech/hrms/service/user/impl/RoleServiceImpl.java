package com.cltech.hrms.service.user.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.user.Role;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.user.RoleRepository;
import com.cltech.hrms.service.user.RoleService;

@Service
@Qualifier("roleServiceImp")
public class RoleServiceImpl implements RoleService {
	
	private static Logger LOGGER=LogManager.getLogger(UserServiceImpl.class);
   
    @Autowired
    private RoleRepository roleRepository;
    
	@Override
	public ResponseBean getRole() {
        try {
       	List<Role> roleBean=roleRepository.getRole();
           if(roleBean.isEmpty()) {
       		return ResponseBean.builder().status(Status.FAIL).message("role is empty").build();
           }
           
   		return ResponseBean.builder().status(Status.SUCCESS).response(roleBean).build();
       	
       }catch(Exception e) {
       	LOGGER.error(e.getMessage(),e);
   		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();

       }
   }


	
	
}
