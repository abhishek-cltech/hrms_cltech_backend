package com.cltech.hrms.service;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;

public interface GroupMasterSerivce {
	ResponseBean saveGroupMaster(GroupMaster employee);
	ResponseBean updateGroupMaster(GroupMaster employee);
	ResponseBean getAllGroupMasterGrid(DataTableRequestBean dataTableRequestBean);
	ResponseBean getGroupMasterById(Long id);
	ResponseBean getGroupMasterByGroupName();
	ResponseBean getSecurityQuestionGroup();
	
}
