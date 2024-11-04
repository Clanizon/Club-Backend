package com.booking.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.SlotBooked;
import com.booking.model.slot.UserBooking;
import com.booking.service.BookingUser;


@Repository
public interface UserBookingDao extends CrudRepository<UserBooking, Long> {

	List<UserBooking> findByUserId(Integer  userid);
	
	List<UserBooking> findByBookingId(Integer  bookingId);


	
	
	 @Query(value = "select count (*) as slotBooked from user_booking ub inner join club_slot cs on ub.slot_id=cs.slot_id where ub.approval_status is null and ub.user_id = :userid and DATE(cs.SLOT_DATE) = DATE(:slotDate)",
		       nativeQuery = true)
				SlotBooked findByUserIdSlotDate(Integer userid ,String slotDate);

	 
	 @Query(value = "SELECT ub.* FROM user_booking ub INNER JOIN club_slot cs ON ub.slot_id = cs.slot_id WHERE ub.approval_status IS NULL AND ub.user_id IN (:userId) AND DATE(cs.SLOT_DATE) = DATE(:slotDate) AND cs.slot_status='Primary Booked'",
		       nativeQuery = true)
	 List<UserBooking> findByUserIdAndSlotDate(@Param("userId") List<Integer> userId, @Param("slotDate") Date slotDate);
	
	 @Query(value = "select count (*) as slotBooked from user_booking ub inner join club_slot cs on ub.slot_id=cs.slot_id where ub.approval_status is null and  cs.slot_start_timestmp >=:curDate and cs.slot_start_timestmp <=:stop  and cs.tee_time =:teeTime",
		       nativeQuery = true)
				SlotBooked findBySlotRangeandTeeTime(
						@Param("curDate") Timestamp curDate,
						 @Param("stop") Timestamp stop,
						 @Param("teeTime") String teeTime);
	
	 @Transactional
	 @Modifying
	 
	 @Query(value = "delete from user_booking where booking_id = :bookingId",
       nativeQuery = true)
		void deleteByBookingId(Integer bookingId);
	 
	 @Transactional
	 @Modifying
	 @Query(value = "delete from user_booking where slot_id = :slotId",
		       nativeQuery = true)
				int deleteBySlotId(Integer slotId);
	 
	 @Transactional
	 @Modifying
	 @Query(value = "update user_booking  set cancelled_by = :cancelledBy, APPROVAL_STATUS = :approvalStatus where booking_id = :bookingId",
		       nativeQuery = true)
	int updateStatus(String cancelledBy,String approvalStatus,Integer bookingId);
	 
	 @Transactional
	 @Modifying
	 @Query(value = "update user_booking  set APPROVAL_STATUS = :approvalStatus where booking_id = :bookingId",
		       nativeQuery = true)
	int updateApprovalStatus(String approvalStatus,Integer bookingId);

	
}