package com.cltech.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.Language;



@Repository
public interface LanguageRepository  extends JpaRepository<Language, Long>{

}
