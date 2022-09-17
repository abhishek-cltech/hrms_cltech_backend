package com.cltech.hrms.repository.CustomRepository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cltech.hrms.bean.GroupMaster;
import com.cltech.hrms.repository.CustomRepository.CustomGroupMasterRepository;

public class CustomGroupMasterRepositoryImpl implements CustomGroupMasterRepository {
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<GroupMaster> getGroupMasterByGroupName(String groupName) {
		String jpql = "SELECT g FROM GroupMaster g  where g.groupName=:groupName";
		   TypedQuery<GroupMaster> query=entityManager.createQuery(jpql,GroupMaster.class);
		    query.setParameter("groupName",groupName);
		   return (query.getResultList()!=null)?query.getResultList():null;
	}
	
	@Override
	public GroupMaster getGroupMasterByCode(String code) {
		String jpql = "SELECT g FROM GroupMaster g  where g.code=:code";
		   TypedQuery<GroupMaster> query=entityManager.createQuery(jpql,GroupMaster.class);
		    query.setParameter("code",code);
		   return (query.getSingleResult()!=null)?query.getSingleResult():null;
	}

}
