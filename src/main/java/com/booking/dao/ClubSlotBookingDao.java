package com.booking.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.slot.ClubSlotBooking;

@Repository
public interface ClubSlotBookingDao extends CrudRepository<ClubSlotBooking, Integer> {
	
	List<ClubSlotBooking> findByUserId(Integer userId);
	
	ClubSlotBooking findByBookingId(Integer bookingId);
	
	List<ClubSlotBooking> findBySlotDate(Date slotdate);
	
	List<ClubSlotBooking> findBySlotDateAndBookingStatusNot(Date slotdate,String Status);
	
	List<ClubSlotBooking>  findByPrimaryBookingIdAndBookingStatus(Integer bookingId,String bookingStatus);
	
	@Transactional
	 @Modifying
	 @Query(value = "delete from CLUB_SLOT_BOOKING where slot_id = :slotId",
		       nativeQuery = true)
				int deleteBySlotId(Integer slotId);

	@Transactional
	 @Modifying
	 @Query(value = "update CLUB_SLOT_BOOKING set booking_status = :bookingStatus where booking_id = :bookingId",
		       nativeQuery = true)
	int updateApprovalStatus(String bookingStatus, Integer bookingId);
	
}