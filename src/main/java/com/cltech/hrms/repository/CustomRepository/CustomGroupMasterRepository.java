package com.cltech.hrms.repository.CustomRepository;

import java.util.List;

import com.cltech.hrms.bean.GroupMaster;

public interface CustomGroupMasterRepository {
	public List<GroupMaster> getGroupMasterByGroupName(String groupName);
	public GroupMaster  getGroupMasterByCode(String code);
	
}
