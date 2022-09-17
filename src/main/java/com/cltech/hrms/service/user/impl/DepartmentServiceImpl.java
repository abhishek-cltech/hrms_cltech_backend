package com.cltech.hrms.service.user.impl;

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

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.DataTableResponseBean;
import com.cltech.hrms.bean.common.DepartmentBean;
import com.cltech.hrms.bean.user.Department;
import com.cltech.hrms.constant.MessageConstant;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.user.DepartmentRepository;
import com.cltech.hrms.service.user.DepartmentService;

@Service
@Qualifier("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {

	private static Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);
	protected final String SORT_DIRECTION_ASC = "ASC";
	protected final String SORT_DIRECTION_DESC = "DESC";

	@Autowired
	private DepartmentRepository departmentRepo;

	@Override
	public ResponseBean saveDepartment(Department department) {
		try {
			if(department.getDepartmentName()==null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Department").build();

			}
			Department bean = departmentRepo.save(department);
			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(bean).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean updateDepartment(Department department) {
		try {

			Optional<Department> findById = departmentRepo.findById(department.getId());
			if(!findById.isPresent()) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();

			}

			if(department.getDepartmentName()==null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Department").build();

			}
			Department bean = departmentRepo.save(department);
			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(bean).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean getAllDepartmentGrid(DataTableRequestBean dataTableRequestBean) {
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

				List<Department> findAll = departmentRepo.findAll(page).toList();
				long count = departmentRepo.count();
				long filteredSize=0;
				if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					filteredSize=findAll.size();
				}else {
					filteredSize=count;
				}
				return ResponseBean.builder().status(Status.SUCCESS)
						.response(DataTableResponseBean.<Department>builder().draw(dataTableRequestBean.getDraw())
								.recordsTotal((int) count).recordsFiltered(filteredSize)
								.data(findAll).build())
						.build();

			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}

	@Override
	public ResponseBean getDepartmentById(Long id) {

		try {
			if(id==null) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Id").build();
			}

			Optional<Department> findById = departmentRepo.findById(id);
			if(!findById.isPresent()) {
				return ResponseBean.builder().status(Status.FAIL).message("Please Provide Valid Id").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(findById.get()).build();

		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}

	}

	@Override
	public ResponseBean getAllDepartment() {
		try {
			List<DepartmentBean> findAll = departmentRepo.findAllDepartment();
			if(!findAll.isEmpty()) {
				return ResponseBean.builder().status(Status.SUCCESS)
						.response(findAll)
						.build();
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}


}
