package com.cltech.hrms.repository.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cltech.hrms.bean.common.EmployeeBean;
import com.cltech.hrms.bean.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email=:email")
	public User findUserByEmail(@Param("email") String email);
	
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
	boolean existsByEmail(@Param("email") String email);
    
    @Query(
			  "SELECT  "
			+"u "
			+ "FROM  #{#entityName} u "
			+ "WHERE "
		    + "CONCAT(u.id, '') LIKE %:query% "
  		    + "OR "
			+ "email LIKE CONCAT('%',:query,'%') "
  		    + "AND "
			+"roleId=:roleId"
   		    //+"GROUP BY :column"
			)
    
	   public List<User> getAllRecruiters(@Param("query") String query, @Param("roleId") Long roleId,   Pageable page);
    
    
    @Query(
			  "SELECT "
			+ "count(u) "
			+ "FROM  #{#entityName} u "
			+ "WHERE "
		    + "CONCAT(u.id, '') LIKE %:query% "
		    + "OR "
			+ "email LIKE CONCAT('%',:query,'%') "
		    + "AND "
			+"u.roleId=:roleId"
 		    //+"GROUP BY :column"
			)
  
	   public long getAllRecruitersCount(@Param("query") String query, @Param("roleId") Long roleId);
    
    
    @Query(
			 "SELECT "
			+ "count(u) "
			+ "FROM  #{#entityName} u "
			+ "WHERE "
			+"u.roleId=:roleId"
			)

	   public long getRecruitersCount( @Param("roleId") Long roleId);

    
    @Query( value=
			 "SELECT "
			+ "count(*) "
			+ "FROM  user u "
			+ "WHERE "
			+"u.deparment_ids IS NOT NULL",nativeQuery = true
			)

	   public long getRecruitersCountHavingTaskCount();
    
       public long findByDeparmentIdsNotNull();

       @Query("SELECT u FROM User u where u.email=:email AND u.questionCode=:queCode AND u.questionAnswer=:queAnswer")
       public User isUserValid(@Param("email") String email,@Param("queCode") String questionCode, @Param("queAnswer") String questionAnswer);
}
