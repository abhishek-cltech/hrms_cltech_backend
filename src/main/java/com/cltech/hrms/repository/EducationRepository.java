package com.cltech.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.Education;



@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{

}
