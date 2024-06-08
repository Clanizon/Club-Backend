package com.booking.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.user.ClubUser;
import com.booking.model.user.MetaData;

@Repository
public interface UserDao extends CrudRepository<ClubUser, Long> {
	
	ClubUser findByUserMobile(String usermobile);
	@Transactional
	@Query(value = "SELECT * FROM CLUB_USER WHERE user_email ILIKE :userEmail", nativeQuery = true)
	ClubUser findByUserEmail(@Param("userEmail")String userEmail);
	ClubUser findByOtp(String otp);

	ClubUser findByUserId(Integer userId);
	
	@Transactional
	Object deleteByUserId(Integer userId);
	
	
	

	 @Transactional
	 @Modifying
	 @Query(value = "select user_id as userId,concat(user_Fname, ' ', user_lname) as userFname,user_lname as userLname,full_name as fullName ,user_mobile as userMobile, membership_number as membershipNumber from club_user order by user_fname asc",
		       nativeQuery = true)
	 
	 List<MetaData> findAllUserList();

	
	

	ClubUser findByUserMobileOrUserEmail(String userName,String UserEmail);
	
	
	
	@Transactional
	 @Modifying
	 @Query(value = "UPDATE club_user set user_password =:password where user_email = :userId",
	            nativeQuery = true)
	int updatePassword(@Param("userId") String userId,@Param("password") String password);
	
	
	
	
}