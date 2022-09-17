package com.cltech.hrms.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cltech.hrms.bean.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("Select r from Role r")
    public List<Role> getRole();
	
	@Query("Select r from Role r where r.id=:roleId")
    public Role getRoleById(@Param("roleId") long roleId );
}
