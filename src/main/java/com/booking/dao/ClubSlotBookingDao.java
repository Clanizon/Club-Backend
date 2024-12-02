package com.booking.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.slot.ClubSlotBooking;

@Repository
public interface ClubSlotBookingDao extends CrudRepository<ClubSlotBooking, Integer> {
	
	List<ClubSlotBooking> findByUserId(Integer userId);
	
	ClubSlotBooking findByBookingId(Integer bookingId);
	
	List<ClubSlotBooking> findBySlotDate(Date slotdate);
	
	@Query(value = "Select csb.booking_id as bookingId,csb.booking_status as bookingStatus,csb.booking_type as bookingtype,csb.created_by as createdBy,"
			+ "csb.created_date as createdDate,csb.player as player,csb.player_count as playerCount, csb.primary_booking_id as primaryBookingId,csb.secondary_booking as secondaryBooking,"
			+ "csb.slot_date as slotDate,csb.slot_id as slotId,csb.user_id as userId,csb.updated_date as updatedDate,csb.updated_by as updatedBy,csb.cancelled_by as cancelledBy,"
			+ "JSON_OBJECT('slotId',cs.slot_id,"
			+ "'clubName',cs.club_name,"
			+ "'createdBy',cs.created_by,"
			+ "'createdDate',cs.created_date,"
			+ "'playerCount',cs.player_count,"
			+ "'primaryBookingId',cs.primary_booking_id,"
			+ "'secondaryBooking',cs.secondary_booking,"
			+ "'slotAvailable',cs.slot_available,"
			+ "'slotDate',cs.slot_date,"
			+ "'slotEndTimestmp',cs.slot_end_timestmp,"
			+ "'slotStartTimestmp',cs.slot_start_timestmp,"
			+ "'slotStatus',cs.slot_status,"
			+ "'teeTime',cs.tee_time) AS clubslot,"
			+ "JSON_OBJECT('userId',cu.user_id,"
			+ "'createdBy',cu.created_by,"
			+ "'createdDate',cu.created_date,"
			+ "'dob',cu.dob"
			+ "'fullName',cu.full_name,"
			+ "'membershipNumber',cu.membership_number,"
			+ "'membershipType',cu.membership_type,"
			+ "'otp',cu.otp,"
			+ "'status',cu.status,'userAddress',cu.user_address,'userCity',cu.user_city,'userEmail',cu.user_email,"
			+ "'userFname',cu.user_fname,'userLname',cu.user_lname,'userMobile',cu.user_mobile) as clubuser"
			+ "FROM club_slot_booking csb JOIN club_user cu ON csb.user_id = cu.user_id JOIN "
			+ "club_slot cs ON csb.slot_id = cs.slot_id where csb.slot_date = :slotDate GROUP BY csb.booking_id, cs.slot_id, cu.user_id",
		       nativeQuery = true)
	List<Map<String, Object>> findAllDetailsBySlotDate(@Param("slotDate") Date slotDate);
	
	
	ClubSlotBooking findBySlotId(Integer integer);
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
	
	@Transactional
	 @Modifying
	 @Query(value = "update CLUB_SLOT_BOOKING set cancelled_by = :cancelledBy,  booking_status = :bookingStatus where booking_id = :bookingId",
		       nativeQuery = true)
	int updateApprovalStatusAndCancelledBy(String cancelledBy,String bookingStatus, Integer bookingId);
	
}