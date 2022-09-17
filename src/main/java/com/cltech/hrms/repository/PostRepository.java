package com.cltech.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.Post;



@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	@Query(value = "SELECT p.employee_id FROM  post p where p.department_id in (?1) ",nativeQuery = true)
	public List<Long> findListOfEmployesIdByListOfIds(List<Long> listofDepartmentIds);
}

