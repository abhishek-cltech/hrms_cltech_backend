package com.cltech.hrms.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.common.DepartmentBean;
import com.cltech.hrms.bean.user.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	    @Query("SELECT d FROM  Department d WHERE concat(d.id,'') in :listofIds")
		public List<Department> findDepartmentsByListOfIds(@Param("listofIds") List<String> listofIds);
	    

	    @Query("SELECT new com.cltech.hrms.bean.common.DepartmentBean( d.id as departmentId, d.departmentName as departmentName) from Department d")
		public List<DepartmentBean> findAllDepartment();
}