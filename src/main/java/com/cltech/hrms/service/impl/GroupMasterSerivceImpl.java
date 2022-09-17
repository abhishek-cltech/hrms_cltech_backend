package com.cltech.hrms.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.DataTableResponseBean;
import com.cltech.hrms.constant.GlobalConstant;
import com.cltech.hrms.constant.MessageConstant;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.GroupMasterRepository;
import com.cltech.hrms.service.GroupMasterSerivce;
import com.cltech.hrms.service.common.impl.BaseServiceImpl;

@Service
@Qualifier("groupMasterSerivceImpl")
public class GroupMasterSerivceImpl extends BaseServiceImpl implements GroupMasterSerivce {
	private static Logger LOGGER = LogManager.getLogger(GroupMasterSerivceImpl.class);
	protected final String SORT_DIRECTION_ASC = "ASC";
	protected final String SORT_DIRECTION_DESC = "DESC";
	@Autowired
	private GroupMasterRepository groupMasterRepo;
	

	
	@Override
	public ResponseBean saveGroupMaster(GroupMaster groupMaster) {
		try {
			GroupMaster bean = groupMasterRepo.save(groupMaster);
			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(bean).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean updateGroupMaster(GroupMaster groupMaster) {
		try {

			Optional<GroupMaster> findById = groupMasterRepo.findById(groupMaster.getId());
			if(!findById.isPresent()) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();

			}

			GroupMaster bean = groupMasterRepo.save(groupMaster);
			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(bean).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean getAllGroupMasterGrid(DataTableRequestBean dataTableRequestBean) {
		try {
			if (dataTableRequestBean != null) {

				dataTableRequestBean.setSearchText(dataTableRequestBean.getSearch().getValue());
				dataTableRequestBean.setSortableColumn(dataTableRequestBean.getColumns()
						.get(dataTableRequestBean.getOrder().get(0).getColumn()).getName());

				if(dataTableRequestBean.getLength()==-1) {
					dataTableRequestBean.setLength(Integer.MAX_VALUE);
				}



				Pageable page = PageRequest.of(dataTableRequestBean.getStart(), dataTableRequestBean.getLength())
						.withSort(Sort
								.by(dataTableRequestBean.getOrder().get(0).getDir().equalsIgnoreCase(SORT_DIRECTION_ASC)
										? Sort.Direction.ASC
												: Sort.Direction.DESC, dataTableRequestBean.getSortableColumn()));

				List<GroupMaster> findAll = groupMasterRepo.findAll(page).toList();
				long count = groupMasterRepo.count();
				long filteredSize=0;
				if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					filteredSize=findAll.size();
				}else {
					filteredSize=count;
				}
				return ResponseBean.builder().status(Status.SUCCESS)
						.response(DataTableResponseBean.<GroupMaster>builder().draw(dataTableRequestBean.getDraw())
								.recordsTotal((int) count).recordsFiltered(filteredSize)
								.data(findAll).build())
						.build();

			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean getGroupMasterById(Long id) {

		try {
			if(id==null) {
				return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.PLEASE__PROVIDE_ID)).build();
			}

			Optional<GroupMaster> findById = groupMasterRepo.findById(id);
			if(!findById.isPresent()) {
				return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.PLEASE_VALID_PROVIDE_ID)).build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(findById.get()).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean getGroupMasterByGroupName() {
		try {
			
			List<GroupMaster> beans = groupMasterRepo.getGroupMasterByGroupName(propertyService.getAppProperty(GlobalConstant.GROUPNAME_RESUME_STATUS));
			if(beans!=null) {
				return ResponseBean.builder().status(Status.SUCCESS).response(beans).build();
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}

	}

	@Override
	public ResponseBean getSecurityQuestionGroup() {
		try {
			List<GroupMaster> beans = groupMasterRepo.getGroupMasterByGroupName(propertyService.getAppProperty(GlobalConstant.SECURITY_QUESTIONS));
			if(beans!=null) {
				return ResponseBean.builder().status(Status.SUCCESS).response(beans).build();
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message(propertyService.getMessage(MessageConstant.SOMETHING_WENT_WRONG)).build();
		}
	}
	
}
