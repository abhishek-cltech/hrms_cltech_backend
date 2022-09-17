package com.cltech.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.repository.CustomRepository.CustomGroupMasterRepository;



@Repository
public interface GroupMasterRepository  extends JpaRepository<GroupMaster, Long> , CustomGroupMasterRepository{
	

}
