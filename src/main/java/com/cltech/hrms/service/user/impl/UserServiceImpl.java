package com.cltech.hrms.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.bean.common.DataTableRequestBean;
import com.cltech.hrms.bean.common.DataTableResponseBean;
import com.cltech.hrms.bean.user.Department;
import com.cltech.hrms.bean.user.Role;
import com.cltech.hrms.bean.user.User;
import com.cltech.hrms.constant.GlobalConstant;
import com.cltech.hrms.constant.MessageConstant;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.EmployeeRepository;
import com.cltech.hrms.repository.user.DepartmentRepository;
import com.cltech.hrms.repository.user.RoleRepository;
import com.cltech.hrms.repository.user.UserRepository;
import com.cltech.hrms.service.common.impl.BaseServiceImpl;
import com.cltech.hrms.service.user.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	protected final String SORT_DIRECTION_ASC = "ASC";
	protected final String SORT_DIRECTION_DESC = "DESC";
	
	private static Logger LOGGER=LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
   
    
    @Override
    @Transactional
    public ResponseBean registration(User user) {
    	try {
    		 if(!user.getPassword().equals(user.getConfirmPassword())) {
	         		return ResponseBean.builder().status(Status.FAIL).message("Password Mismatch").build();
           }
    		boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
    		if(existsByEmail) {
        		return ResponseBean.builder().status(Status.FAIL).message("User Already Exist").build();
    		}
    		Role roleById = roleRepository.getRoleById(user.getRoleId());
    		if(roleById==null) {
        		return ResponseBean.builder().status(Status.FAIL).message("Please Provid Proper role").build();
    		}
    		
    		// from admin side registration of recruiter
    		if(user.getDeparmentIds()!=null) {
    			List<String> ids=Arrays.asList(user.getDeparmentIds().split(","));
                List<Department> findDepartmentsByListOfIds = departmentRepository.findDepartmentsByListOfIds(ids);
               
                if(findDepartmentsByListOfIds.isEmpty()) {
                	return ResponseBean.builder().status(Status.FAIL).message("please provide valid department").build();
                }
                
                List<String> departments=new ArrayList<String>();
                  findDepartmentsByListOfIds.forEach((department)->{
                	  departments.add(department.getDepartmentName());
                });
                  
                  String listOfDepartment=String.join(",", departments);
                   user.setDepartments(listOfDepartment);
    		}
    		
    		if(Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_ADMIN_ID))==roleById.getId()) {
    			user.setReturnUrl(propertyService.getAppProperty(GlobalConstant.ADMIN_RETURN_URL));
    		}else if(Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_RECRUITER_ID))==roleById.getId()) {
    			user.setReturnUrl(propertyService.getAppProperty(GlobalConstant.RECRUITER_RETURN_URL));
    		}else if(Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_EMPLOYEE_ID))==roleById.getId()) {
    			user.setReturnUrl(propertyService.getAppProperty(GlobalConstant.EMPLOYEE_RETURN_URL));
    		}else {
    		}
		    user.setRole(roleById.getName());
    		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User userBean = userRepository.save(user);
           
    		return ResponseBean.builder().status(Status.SUCCESS).message("Register Succesfully").response(userBean).build();
    		
    	}catch(Exception e) {
    		LOGGER.error(e.getMessage(),e);
    		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").response(user).build();
    	}
        
    }

    @Override
    public ResponseBean findByUsername(String username) {
        try {
        	User userLogin = userRepository.findUserByEmail(username);
            if(userLogin==null) {
        		return ResponseBean.builder().status(Status.FAIL).message("Invalid Credential").build();
            }
    		return ResponseBean.builder().status(Status.SUCCESS).message("Login Successfull ").response(userLogin).build();
        }catch(Exception e) {
        	LOGGER.error(e.getMessage(),e);
    		return ResponseBean.builder().status(Status.FAIL).message("somthing went wrong").build();
        }
    }

	@Override
	public ResponseBean loginAuthentication(User userForm) {
		try {
			String email = userForm.getEmail();
			 User userLogin = userRepository.findUserByEmail(userForm.getEmail());
		      if(userLogin!=null) {
		    	if(!bCryptPasswordEncoder.matches(userForm.getPassword(), userLogin.getPassword())) {
		    		return ResponseBean.builder().status(Status.FAIL).message("Invalid Credential").build();
		    	}
		    	if(Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_ADMIN_ID))==userLogin.getRoleId()) {
		    		userLogin.setReturnUrl(propertyService.getAppProperty(GlobalConstant.ADMIN_RETURN_URL));
	    		}
		    	
				return ResponseBean.builder().status(Status.SUCCESS).message("Login Successfull ").response(userLogin).build();
		      }
				return ResponseBean.builder().status(Status.FAIL).message("Invalid Credential").build();
			
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseBean.builder().status(Status.FAIL).message("something went wrong").build();
		}
	}
	
	@Override
	public ResponseBean getAllUser(DataTableRequestBean dataTableRequestBean) {
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
				
				    long recruiterId=Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_RECRUITER_ID));
				    List<User> recriuterList = userRepository.getAllRecruiters(dataTableRequestBean.getSearchText(),recruiterId,page);
				    
				    for (User user : recriuterList) {
				    	if(user.getDeparmentIds()!=null && !user.getDeparmentIds().trim().equals("") ) {
				    		List<String> ids=Arrays.asList(user.getDeparmentIds().split(","));
				            List<Department> findDepartmentsByListOfIds = departmentRepository.findDepartmentsByListOfIds(ids);
				            user.setDepartment(findDepartmentsByListOfIds);
				    	}
				     }
					long count = userRepository.getAllRecruitersCount(dataTableRequestBean.getSearchText(),recruiterId);
					long filteredSize=0;
					if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					 filteredSize=recriuterList.size();
					 }else {
					 filteredSize=count;
					 }
					return ResponseBean.builder().status(Status.SUCCESS)
							.response(DataTableResponseBean.<User>builder().draw(dataTableRequestBean.getDraw())
									.recordsTotal((int) count).recordsFiltered(filteredSize)
									.data(recriuterList).build())
							.build();
					
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}

	@Override
	public ResponseBean findById(long id) {
        try {
        	 Optional<User> userBean=userRepository.findById(id);
            if(!userBean.isPresent()) {
        		return ResponseBean.builder().status(Status.FAIL).message("please provide valid id").build();
            }
            
    		return ResponseBean.builder().status(Status.SUCCESS).response(userBean.get()).build();
        	
        }catch(Exception e) {
        	LOGGER.error(e.getMessage(),e);
    		return ResponseBean.builder().status(Status.FAIL).message("somthing went wrong").build();

        }
    }

	@Override
	public ResponseBean updateUserDepartment(User user) {
        try {
       	   Optional<User> userBean=userRepository.findById(user.getId());
           if(!userBean.isPresent()) {
       		return ResponseBean.builder().status(Status.FAIL).message("please provide valid id").build();
           }
           
           if(user.getDeparmentIds()==null) {
          		return ResponseBean.builder().status(Status.FAIL).message("please provide department").build();
           }
           
            List<String> ids=Arrays.asList(user.getDeparmentIds().split(","));
            List<Department> findDepartmentsByListOfIds = departmentRepository.findDepartmentsByListOfIds(ids);
           
            if(findDepartmentsByListOfIds.isEmpty()) {
            	return ResponseBean.builder().status(Status.FAIL).message("please provide valid department").build();
//            	findDepartmentsByListOfIds.add(null);   
            	}
            
            List<String> departments=new ArrayList<String>();
              findDepartmentsByListOfIds.forEach((department)->{
            	  departments.add(department.getDepartmentName());
            });
              
              String listOfDepartment=String.join(",", departments);
               userBean.get().setDepartments(listOfDepartment);
               userBean.get().setDeparmentIds(user.getDeparmentIds());
               //userBean.get().setActive(user.getActive());
               User  bean= userRepository.save(userBean.get());
   		   return ResponseBean.builder().status(Status.SUCCESS).message("user Department updated Successfully").response(bean).build();
       	
       }catch(Exception e) {
       	LOGGER.error(e.getMessage(),e);
   		return ResponseBean.builder().status(Status.FAIL).message("somthing went wrong").build();

       }
   }
	
	@Override
	public ResponseBean getAllRegisterUser(DataTableRequestBean dataTableRequestBean) {
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
				
				    long registerUserId=Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_EMPLOYEE_ID));
				    List<User> registerUserList = userRepository.getAllRecruiters(dataTableRequestBean.getSearchText(),registerUserId,page);
	
					long count = userRepository.getAllRecruitersCount(dataTableRequestBean.getSearchText(),registerUserId);
					long filteredSize=0;
					if(dataTableRequestBean.getSearchText() != null && !dataTableRequestBean.getSearchText().trim().equals("") ) {
					 filteredSize=registerUserList.size();
					 }else {
					 filteredSize=count;
					 }
					return ResponseBean.builder().status(Status.SUCCESS)
							.response(DataTableResponseBean.<User>builder().draw(dataTableRequestBean.getDraw())
									.recordsTotal((int) count).recordsFiltered(filteredSize)
									.data(registerUserList).build())
							.build();
					
			}
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.RECORD_NOT_FOUND).build();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message(MessageConstant.SOMETHING_WENT_WRONG).build();
		}

	}

	
	 @Override
	    @Transactional
	    public ResponseBean updateProfile(User user) {
	    	try {
	    		
	    		if(user.getId()==null) {
	         		return ResponseBean.builder().status(Status.FAIL).message("please provide id").build();
	    		 }
	    		 Optional<User> bean=userRepository.findById(user.getId());
	             if(!bean.isPresent()) {
	         		return ResponseBean.builder().status(Status.FAIL).message("please provide valid id").build();
	             }    		
	             bean.get().setFirstName(user.getFirstName());
	             bean.get().setLastName(user.getLastName());
	            
	             User userBean = userRepository.save( bean.get());
	    		 return ResponseBean.builder().status(Status.SUCCESS).message("Profile Updated").response(userBean).build();
	    		
	    	}catch(Exception e) {
	    		LOGGER.error(e.getMessage(),e);
	    		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
	    	}
	        
	    }
	 
	 @Override
	    @Transactional
	    public ResponseBean resetPassword(User user) {
	    	try {
	    		 if(!user.getPassword().equals(user.getConfirmPassword())) {
		         		return ResponseBean.builder().status(Status.FAIL).message("Password Mismatch").build();
	             }
	    		 if(user.getId()==null) {
	         		return ResponseBean.builder().status(Status.FAIL).message("please provide id").build();
	    		 }
	    		 Optional<User> bean=userRepository.findById(user.getId());
	             if(!bean.isPresent()) {
	         		return ResponseBean.builder().status(Status.FAIL).message("please provide valid id").build();
	             } 
	             bean.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	             User userBean = userRepository.save(bean.get());
	    		return ResponseBean.builder().status(Status.SUCCESS).message("Password Reset successfully").response(userBean).build();
	    		
	    	}catch(Exception e) {
	    		LOGGER.error(e.getMessage(),e);
	    		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
	    	}
	        
	    }

	@Override
	public ResponseBean getDashBoardData() {
		try {
			
			Long totalResume;
			Long selectedApplicantCount;
			Long recruiterCount;
			Long recruiterCountHavingTask;
			totalResume=employeeRepository.count();
			selectedApplicantCount = employeeRepository.getSelectedApplicantCount(propertyService.getAppProperty(GlobalConstant.SELECTED_APPLICANT_STATUS));
			recruiterCount=userRepository.getRecruitersCount(Long.parseLong(propertyService.getAppProperty(GlobalConstant.ROLE_RECRUITER_ID)));
			recruiterCountHavingTask=userRepository.getRecruitersCountHavingTaskCount();
			Long freshResume=totalResume-selectedApplicantCount;
			Long recruiterWithoutTask=recruiterCount-recruiterCountHavingTask;
			Map<String,Object> map=new LinkedHashMap<String,Object>();
			
			map.put("totalResume", totalResume);
			map.put("selectedResume", selectedApplicantCount);
			map.put("freshResume", freshResume);
			map.put("totalRecruiter", recruiterCount);
			map.put("recruiterHavingTask", recruiterCountHavingTask);
			map.put("recruiterWithoutTask", recruiterWithoutTask);
			
			return ResponseBean.builder().status(Status.SUCCESS).response(map).build();
		}catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
    		return ResponseBean.builder().status(Status.FAIL).message("Something went wrong").build();
		}
		
	}

	@Override
	public ResponseBean getUser(User user) {
		 try {
	        	User userValid = userRepository.isUserValid(user.getEmail(),user.getQuestionCode(),user.getQuestionAnswer());
	            if(userValid==null) {
	        		return ResponseBean.builder().status(Status.FAIL).message("Invalid Details").build();
	            }
	    		return ResponseBean.builder().status(Status.SUCCESS).message("Successfully Verified !").response(userValid).build();
	        }catch(Exception e) {
	        	LOGGER.error(e.getMessage(),e);
	    		return ResponseBean.builder().status(Status.FAIL).message("somthing went wrong").build();
	        }
	}

	@Override
	public ResponseBean removeDeptfromUser(Long id) {
		try {
       	 Optional<User> userBean=userRepository.findById(id);
           if(!userBean.isPresent()) {
       		return ResponseBean.builder().status(Status.FAIL).message("please provide valid id").build();
           }
           userBean.get().setDeparmentIds(null);
           userBean.get().setDepartments(null);
           User user = userRepository.save(userBean.get());
   		return ResponseBean.builder().status(Status.SUCCESS).response(user).message("Department Removed From User").build();
       	
       }catch(Exception e) {
       	LOGGER.error(e.getMessage(),e);
   		return ResponseBean.builder().status(Status.FAIL).message("somthing went wrong").build();

       }
	}
}
