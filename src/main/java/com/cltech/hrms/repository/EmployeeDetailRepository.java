package com.cltech.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.EmployeeDetail;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {

}
