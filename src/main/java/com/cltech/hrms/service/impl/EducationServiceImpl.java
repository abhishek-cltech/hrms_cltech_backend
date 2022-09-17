package com.cltech.hrms.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cltech.hrms.bean.Education;
import com.cltech.hrms.bean.ResponseBean;
import com.cltech.hrms.constant.Status;
import com.cltech.hrms.repository.EducationRepository;

@Service
public class EducationServiceImpl {

	

	private static Logger LOGGER = LogManager.getLogger(EducationServiceImpl.class);

	@Autowired
	private EducationRepository educationRepository;

	public ResponseBean addEducation(Education ed) {

		try {
			// Education ed2 = educationRepo.save(ed);

			System.out.println(ed);
			if (ed == null) {
				return ResponseBean.builder().status(Status.FAIL).message("Kindly Provide the Language Details")
						.build();
			} else {
				Education educationBean = educationRepository.save(ed);
				return ResponseBean.builder().status(Status.SUCCESS).message("Record has been Added Successfully")
						.response(educationBean).build();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something Went Wrong").build();
		}
	}

	public ResponseBean updateEducation(Education ed) {
		try {
			if (ed == null) {
				return ResponseBean.builder().status(Status.FAIL).message("null").build();
			}
			if (ed.getId() > 0) {
				Optional<Education> findById = educationRepository.findById( ed.getId());
				if (findById != null) {
					Education educationBean = educationRepository.save(findById.get());
					return ResponseBean.builder().status(Status.SUCCESS).message("Record updated Successfully")
							.response(educationBean).build();
				}

			} else {
				return ResponseBean.builder().status(Status.FAIL).message("please provide id").build();
			}
			return ResponseBean.builder().status(Status.FAIL).message("Record Not Found").build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something Went Wrong").build();
		}
	}

	public ResponseBean deleteById(long id) {

		try {
			educationRepository.deleteById(id);
			return ResponseBean.builder().status(Status.SUCCESS).message("Deleted Successfully").build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something Went Wrong").build();
		}
	}

	public ResponseBean getAllEducationDetails() {
		List<Education> list = educationRepository.findAll();

		try {
			if (list.size() <= 0) {
				return ResponseBean.builder().status(Status.FAIL).message("Data is Null").build();
			}

			else {
				/* List<Education> list2 = educationRepo.findAll(); */
				return ResponseBean.builder().status(Status.SUCCESS).message("This is the List of EdcuationDetails")
						.response(list).build();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something Went Wrong").build();
		}
	}

	public ResponseBean getEducationDetailsById(long id) {

		Education ed = educationRepository.findById(id).get();

		try {
			if (ed == null) {
				return ResponseBean.builder().status(Status.FAIL).message("No Data Found With Given Id").build();
			} else {
				return ResponseBean.builder().status(Status.SUCCESS)
						.message("This is the data which found from Databases").response(ed).build();
			}

		} catch (Exception e) {

			LOGGER.error(e.getMessage(), e);
			return ResponseBean.builder().status(Status.FAIL).message("Something Went Wrong").build();
		}
	}
}
