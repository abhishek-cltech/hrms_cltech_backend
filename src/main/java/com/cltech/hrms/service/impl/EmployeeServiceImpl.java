package com.cltech.hrms.service.impl;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.cltech.hrms.excelupload.helper.ExcelHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.Employee;
import com.cltech.hrms.bean.Experience;
import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.bean.Post;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.DataTableResponseBean;
import com.cltech.hrms.bean.common.EmployeeBean;
import com.cltech.hrms.bean.common.GridDataResponseBean;
import com.cltech.hrms.bean.common.GridDatatableRequestBean;
import com.cltech.hrms.bean.user.User;
import com.cltech.hrms.constant.GlobalConstant;
import com.cltech.hrms.constant.MessageConstant;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.EmployeeRepository;
import com.cltech.hrms.repository.GroupMasterRepository;
import com.cltech.hrms.repository.PostRepository;
import com.cltech.hrms.repository.user.UserRepository;
import com.cltech.hrms.service.EmployeeService;
import com.cltech.hrms.service.common.impl.BaseServiceImpl;
import com.cltech.hrms.utility.CommonUtility;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
	private static Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

	protected final String SORT_DIRECTION_ASC = "ASC";
	protected final String SORT_DIRECTION_DESC = "DESC";

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PostRepository postRepository;
	
	@Autowired
	private GroupMasterRepository groupMasterRepo;
	

	@Override
	@Transactional
	public ResponseBean saveEmployee(Employee employee) {
		try {
			if (employee == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Record is empty").build();
			}
			String skillString = String.join(", ",
					employee.getSkills().stream().map((skill) -> skill.getSkillName()).collect(Collectors.toList()));
			employee.setTotalSkill(skillString);
			 double totalExperience=0;
			if (employee.getExperiences() != null && !employee.getExperiences().isEmpty()) {
				
				for (Experience experience : employee.getExperiences()) {
					totalExperience+=calculateExperienceInYear(experience.getStartDate(),experience.getEndDate());
				}
				employee.setTotalExperience(totalExperience);
			}
			
           if (employee.getPosts() != null && !employee.getPosts().isEmpty()) {
        	   String postString = String.join(", ",
   					employee.getPosts().stream().map(Post::getDepartmentName).collect(Collectors.toList()));
				employee.setLookingFor(postString);
			}


			Employee employeeBean = employeeRepository.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record Added Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().message(MessageConstant.SOMETHING_WENT_WRONG).status(Status.FAIL).build();
		}

	}

	@Override
	@Transactional
	public ResponseBean updateEmployee(Employee employee) {
		try {
			if (employee.getId() == null) {
				return ResponseBean.builder().message("please provid Id").status(Status.FAIL).build();
			}

			Optional<Employee> findById = employeeRepository.findById(employee.getId());
			if (!findById.isPresent()) {
				return ResponseBean.builder().message("please provid valid Id").status(Status.FAIL).build();
			}
			
			String skillString = String.join(", ",
					employee.getSkills().stream().map((skill) -> skill.getSkillName()).collect(Collectors.toList()));
			employee.setTotalSkill(skillString);
			 double totalExperience=0;
			if (employee.getExperiences() != null && !employee.getExperiences().isEmpty()) {
				
				for (Experience experience : employee.getExperiences()) {
					totalExperience+=calculateExperienceInYear(experience.getStartDate(),experience.getEndDate());
				}
				employee.setTotalExperience(totalExperience);
			}
			
			if (employee.getPosts() != null && !employee.getPosts().isEmpty()) {
	        	   String postString = String.join(", ",
	   					employee.getPosts().stream().map(Post::getDepartmentName).collect(Collectors.toList()));
					employee.setLookingFor(postString);
				}

			Employee employeeBean = employeeRepository.save(employee);
			return ResponseBean.builder().status(Status.SUCCESS).message("Record updated Succesfully")
					.response(employeeBean).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().message(MessageConstant.SOMETHING_WENT_WRONG).status(Status.FAIL).build();
		}
	}

	@Override
	@Transactional
	public ResponseBean getAllEmployee(GridDatatableRequestBean gridDatatableRequestBean) {
		try {
			long count = employeeRepository.count();
			List<Employee> employees = employeeRepository.findAll();
			return ResponseBean.builder().status(Status.SUCCESS)
					.response(
							GridDataResponseBean.<Employee>builder().totalRecords((int) count).rows(employees).build())
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();

		}
	}

	@Override
	@Transactional
	public ResponseBean getEmployeeById(Long id) {
		try {
			if (id == null) {
				return ResponseBean.builder().message("please provid Id").status(Status.FAIL).build();
			}

			Optional<Employee> findById = employeeRepository.findById(id);
			if (findById == null) {
				return ResponseBean.builder().message("please provid valid Id").status(Status.FAIL).build();
			}
			return ResponseBean.builder().status(Status.SUCCESS).response(findById.get()).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().message(MessageConstant.SOMETHING_WENT_WRONG).status(Status.FAIL).build();
		}
	}

	@Override
	public ResponseBean getAllEmployees(DataTableRequestBean dataTableRequestBean) {
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
				
				
				List<Long> departmentsIds=null; 
				if(dataTableRequestBean.getExtraParam()!=null && !dataTableRequestBean.getExtraParam().trim().equals("") ) {
					User findUserByEmail = userRepository.findUserByEmail(dataTableRequestBean.getExtraParam());
					if(findUserByEmail!=null && findUserByEmail.getDeparmentIds()!=null) {
						 List<String> asList = Arrays.asList(findUserByEmail.getDeparmentIds().split(","));
						 departmentsIds = asList.stream().map(element->{
							 return Long.parseLong(element);
						 }).collect(Collectors.toList());
					}
				}
				
				     List<Long> findListOfEmployesIdByListOfIds = postRepository.findListOfEmployesIdByListOfIds(departmentsIds);
				     List<EmployeeBean> allEmployees = employeeRepository.getAllEmployees(dataTableRequestBean.getSearchText(),findListOfEmployesIdByListOfIds,page);
					//@Param("column") String column,dataTableRequestBean.getSortableColumn(),
					long count = employeeRepository.getAllEmployeeCount(dataTableRequestBean.getSearchText(),findListOfEmployesIdByListOfIds);
					long filteredSize=0;
					if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					 filteredSize=allEmployees.size();
					 }else {
					 filteredSize=count;
					 }
					return ResponseBean.builder().status(Status.SUCCESS)
							.response(DataTableResponseBean.<EmployeeBean>builder().draw(dataTableRequestBean.getDraw())
									.recordsTotal((int) count).recordsFiltered(filteredSize)
									.data(allEmployees).build())
							.build();
					
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}
	
	public double  calculateExperienceInYear(String sDate,String eDate) {
		try {
			DecimalFormat f = new DecimalFormat("##.00");
			Date parseEdate=new Date();
			if(eDate!=null) {
				parseEdate = CommonUtility.parseDate(eDate);
			}
			Date parseSdate = CommonUtility.parseDate(sDate);
			long timeDiff=parseEdate.getTime() - parseSdate.getTime();
			long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			float c= (float) (daysDiff/365.0);
			String format = f.format(c);
			return  Double.parseDouble(format);
		}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			return 0.0;
		}
		
	}
	
	@Override
	public ResponseBean getAllEmployeesForAdmin(DataTableRequestBean dataTableRequestBean) {
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
				
				     List<EmployeeBean> allEmployees = employeeRepository.getAllEmployeesForAdmin(dataTableRequestBean.getSearchText(),page);
					//@Param("column") String column,dataTableRequestBean.getSortableColumn(),
					long count = employeeRepository.getAllEmployeeCountForAdmin(dataTableRequestBean.getSearchText());
					long filteredSize=0;
					if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					 filteredSize=allEmployees.size();
					 }else {
					 filteredSize=count;
					 }
					return ResponseBean.builder().status(Status.SUCCESS)
							.response(DataTableResponseBean.<EmployeeBean>builder().draw(dataTableRequestBean.getDraw())
									.recordsTotal((int) count).recordsFiltered(filteredSize)
									.data(allEmployees).build())
							.build();
					
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}

	@Override
	public ResponseBean updateEmployeeResumeStatus(Employee employee) {
		try {
			if (employee.getId() == null) {
				return ResponseBean.builder().message("please provid Id").status(Status.FAIL).build();
			}
			GroupMaster groupMasterByGroupName = groupMasterRepo.getGroupMasterByCode(employee.getResumeStatusCode());
			if(groupMasterByGroupName==null) {
				return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();
			}
			Optional<Employee> findById = employeeRepository.findById(employee.getId());
			if (!findById.isPresent()) {
				return ResponseBean.builder().message("please provid valid Id").status(Status.FAIL).build();
			}
			findById.get().setResumeStatusCode(employee.getResumeStatusCode());
			findById.get().setResumeStatusValue(groupMasterByGroupName.getValue());
			Employee employeeBean = employeeRepository.save(findById.get());
			return ResponseBean.builder().response(employeeBean).status(Status.SUCCESS).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().message(MessageConstant.SOMETHING_WENT_WRONG).status(Status.FAIL).build();
		}
	
	}
	
	@Override
	public ResponseBean getAllSelectedApplicantReport(DataTableRequestBean dataTableRequestBean) {
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
				
				     List<EmployeeBean> allEmployees = employeeRepository.getAllSelectedApplicantReport(dataTableRequestBean.getSearchText(),page,propertyService.getAppProperty(GlobalConstant.SELECTED_APPLICANT_STATUS));
					//@Param("column") String column,dataTableRequestBean.getSortableColumn(),
					long count = employeeRepository.getAllSelectedApplicantReportCount(dataTableRequestBean.getSearchText(),propertyService.getAppProperty(GlobalConstant.SELECTED_APPLICANT_STATUS));
					long filteredSize=0;
					if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					 filteredSize=allEmployees.size();
					 }else {
					 filteredSize=count;
					 }
					return ResponseBean.builder().status(Status.SUCCESS)
							.response(DataTableResponseBean.<EmployeeBean>builder().draw(dataTableRequestBean.getDraw())
									.recordsTotal((int) count).recordsFiltered(filteredSize)
									.data(allEmployees).build())
							.build();
					
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}

	@Override
	public ResponseBean saveBulkEmployee(List<Employee> employees) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBean getApplicant(Employee employee) {
		try {
			
			if(employee.getEmployeeDetail().getEmail()!=null && !"".equals(employee.getEmployeeDetail().getEmail()) ) {
				List<EmployeeBean> employees = employeeRepository.getApplicant(employee.getEmployeeDetail().getEmail());
				return ResponseBean.builder().status(Status.SUCCESS)
						.response(employees)
						.build();
			}
			return ResponseBean.builder().status(Status.FAIL)
					.message(MessageConstant.RECORD_NOT_FOUND)
					.build();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();

		}
	}

	@Override
	public ResponseBean uploadEmployeeExcel(MultipartFile file) {
		try{
			Map<String, Object> erorrMap = new HashMap<>();
			Map<String, Object> sucessMap = new HashMap<>();
			int count=1;
			List<Employee> employees = ExcelHelper.convertExcelToEmployee(file.getInputStream());

			for (Employee employee : employees) {

				ResponseBean saveEmployee = saveEmployee(employee);
				if(saveEmployee.getResponse()==null && saveEmployee.getStatus().equals(Status.FAIL)) {
					erorrMap.put("row "+count, saveEmployee.getMessage());
				}
				else {
					sucessMap.put("row "+count, saveEmployee.getMessage());
				}
				count++;

			}
			if(erorrMap!=null && erorrMap.size()>0) {
				return ResponseBean.builder().status(Status.FAIL).response(erorrMap).message("PARTIALLY_UPDATED").build();
			}

			return ResponseBean.builder().status(Status.SUCCESS).message(MessageConstant.EXCEL_UPLOADED).build();
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}
}
