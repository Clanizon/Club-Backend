package com.booking.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.user.ClubUser;

@Repository
public interface UserDao extends CrudRepository<ClubUser, Long> {
	ClubUser findByUserMobile(String usermobile);
	ClubUser findByUserId(Integer userId);
	
	ClubUser findByUserMobileOrUserEmail(String userName,String UserEmail);
	
	
	
	@Transactional
	 @Modifying
	 @Query(value = "UPDATE ownnten_user set user_password =:password where user_mobile = :userId",
	            nativeQuery = true)
	int updatePassword(@Param("userId") String userId,
			 @Param("password") String password);
}