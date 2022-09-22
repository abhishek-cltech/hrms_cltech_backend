package com.cltech.hrms.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.Employee;
import com.cltech.hrms.bean.common.EmployeeBean;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(
			  "SELECT "
			+ "new com.cltech.hrms.bean.common.EmployeeBean("
			+ "e.id,e.totalExperience,e.totalSkill, e.lookingFor, "
			+ "concat(d.firstName, ' ',d.lastName) as name, "
			+ "d.email  as email, d.phone as phone,p.departmentName,e.resumeStatusCode,e.resumeStatusValue "
			+ ") "
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
            + "( CONCAT(e.id, '') LIKE %:query% "
  		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
     		+"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
		    + "OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
    		+"CONCAT(e.totalExperience, '') LIKE %:query% ) "
            +"AND "
			+ "e.id IN :departmentsIds  "
			//+ "OR "
			//+ "(:departmentsIds is not null OR p.departmentId in:departmentsIds) "
//     		+"GROUP BY e.id"
			
			)
	   public List<EmployeeBean> getAllEmployees(@Param("query") String query, @Param("departmentsIds") List<Long> departmentsIds, Pageable page);
	
//	@Query("select kaliId from Allotment where donorId =?1 and statTerm IN (?2)")
//	List<Long> findByDonorIdAndStatTerm(Long donorId, List<String> statTermList)
//	
	
	@Query(
			  "SELECT "
			+ "count(DISTINCT e)"
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
            + "( CONCAT(e.id, '') LIKE %:query% "
  		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
     		+"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
		    + "OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
    		+"CONCAT(e.totalExperience, '') LIKE %:query% ) "
            +"AND "
			+ "e.id IN :departmentsIds  "
     		//+"GROUP BY e.id"
			
			)
	   public long getAllEmployeeCount(@Param("query") String query,@Param("departmentsIds") List<Long> departmentsIds);
         // for admin call
	
	  @Query(
			  "SELECT "
			+ "new com.cltech.hrms.bean.common.EmployeeBean("
			+ "e.id,e.totalExperience,e.totalSkill,e.lookingFor, "
			+ "concat(d.firstName, ' ',d.lastName) as name, "
			+ "d.email  as email, d.phone as phone,p.departmentName,e.resumeStatusCode,e.resumeStatusValue "
			+ ") "
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
            + "CONCAT(e.id, '') LIKE %:query% "
		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
   		    +"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
			+"OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
  		    +"CONCAT(e.totalExperience, '') LIKE %:query%  "
//   		    +"GROUP BY e.id"
			)
	   public List<EmployeeBean> getAllEmployeesForAdmin(@Param("query") String query, Pageable page);
	

	@Query(
			  "SELECT "
			+ "count(DISTINCT e)"
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
            + "CONCAT(e.id, '') LIKE %:query% "
		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
   		    +"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
			+"OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
  		    +"CONCAT(e.totalExperience, '') LIKE %:query%  "
            //+"GROUP BY e.id"
			)
	   public long getAllEmployeeCountForAdmin(@Param("query") String query);
	
	////report query
	 @Query(
			  "SELECT "
			+ "new com.cltech.hrms.bean.common.EmployeeBean("
			+ "e.id,e.totalExperience,e.totalSkill,e.lookingFor, "
			+ "concat(d.firstName, ' ',d.lastName) as name, "
			+ "d.email  as email, d.phone as phone,p.departmentName,e.resumeStatusCode,e.resumeStatusValue "
			+ ") "
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
			+ "e.resumeStatusCode=:status "
			+ "AND "
            + "( CONCAT(e.id, '') LIKE %:query% "
		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
  		    +"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
			+"OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
 		    +"CONCAT(e.totalExperience, '') LIKE %:query% ) "
//  		    +"GROUP BY e.id"
			)
	   public List<EmployeeBean> getAllSelectedApplicantReport(@Param("query") String query, Pageable page,@Param("status") String status);
	

	@Query(
			  "SELECT "
			+ "count(DISTINCT e)"
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
			+ " e.resumeStatusCode=:status "
			+ "AND "
            + "( CONCAT(e.id, '') LIKE %:query% "
		    + "OR "
	        + "d.firstName LIKE CONCAT('%',:query,'%') "
            + "OR "
	        + "d.email LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusCode LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "e.resumeStatusValue LIKE CONCAT('%',:query,'%') "
			+ "OR "
			+ "d.phone LIKE CONCAT('%',:query,'%') "
  		    +"OR "
			+"e.totalSkill LIKE CONCAT('%',:query,'%') "
			+"OR "
		    +"e.lookingFor LIKE CONCAT('%',:query,'%') "
		    + "OR "
 		    +"CONCAT(e.totalExperience, '') LIKE %:query% ) "
           //+"GROUP BY e.id"
			)
	    public long getAllSelectedApplicantReportCount(@Param("query") String query,@Param("status") String status);
	
	
	@Query(
			  "SELECT "
			+ "count(e)"
			+ "FROM  Employee e "
			+ "WHERE "
			+ " e.resumeStatusCode=:status "
			)
	    public long getSelectedApplicantCount(@Param("status") String status);

	@Query(
			  "SELECT "
			+ "new com.cltech.hrms.bean.common.EmployeeBean("
			+ "e.id,e.totalExperience,e.totalSkill, e.lookingFor, "
			+ "concat(d.firstName, ' ',d.lastName) as name, "
			+ "d.email  as email, d.phone as phone,p.departmentName,e.resumeStatusCode,e.resumeStatusValue "
			+ ") "
			+ "FROM  Employee e "
			+ "JOIN e.posts p "
			+ "JOIN e.employeeDetail d "
			+ "WHERE "
			+ "d.email=:email "
			+ "OR "
			+ "d.alternateEmail=:email "
			+ "Order BY e.createdDt "
			+ "DESC"
			)
	       public List<EmployeeBean> getApplicant(@Param("email") String email);
	
}
