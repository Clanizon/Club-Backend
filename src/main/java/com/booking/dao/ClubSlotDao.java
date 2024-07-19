package com.booking.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.SlotBooked;
import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotModel;


@Repository
public interface ClubSlotDao extends CrudRepository<ClubSlot, Integer> {
	

	List <ClubSlot> findBySlotDateBefore(Timestamp slotDate);
	
	List <ClubSlot> findBySlotDateBeforeAndSlotDateAfterAndSlotAvailable(Timestamp slotDate,Timestamp curDate,String slotAvailable);
	List <ClubSlot> findBySlotStartTimeStampAfterOrderBySlotStartTimeStampAsc(Timestamp slotDate);
	
	
	
	 @Query(value = "UPDATE CLUB_SLOT set SECONDARY_BOOKING=:secondaryBooking , SLOT_STATUS = :slotStatus,primary_booking_id  =:primaryBookingId, PLAYER_COUNT =PLAYER_COUNT +:playerCount where SLOT_ID =:slotId",
		     nativeQuery = true) 
	 List <ClubSlot> findAdminSlot( String secondaryBooking,@Param("primaryBookingId") Integer primaryBookingId);
			
	
	 
			 @Query(value = " select cs.slot_id as slotId, cs.club_name as clubName, "
			 		+ "cs.created_by as createdBy, cs.created_date as createdDate, cs.player_count as playerCount, "
			 		+ "cs.primary_booking_id as primaryBookingId, cs.secondary_booking as secondaryBooking, cs.slot_available as "
			 		+ "slotAvailable, cs.slot_date as slotDate, cs.slot_end_timestmp as slotEndTimestmp, cs.slot_start_timestmp as slotStartTimeStamp, "
			 		+ "cs.slot_status as slotStatus, cs.tee_time as teeTime from club_slot cs where cs.slot_start_timestmp >:curDate order by cs.slot_start_timestmp asc\n",
			 		
		       nativeQuery = true)
			 List <ClubSlotModel> findAdminSlot(Timestamp curDate);
			 
			 
			 
			 
			
	 
	
	
	//List <ClubSlot> findBySlotStartTimeStampAfterAndSlotAvailableOrderBySlotStartTimeStampAsc(Timestamp curDate,String slotAvailable);
	List <ClubSlot>	findBySlotStartTimeStampAfterAndSlotAvailableAndSlotStatusNotOrderBySlotStartTimeStampAsc(Timestamp slotStartTimeStamp, String slotAvailable, String slotStatus);
	
	 @Query(value = " select cs.slot_id as slotId, cs.club_name as clubName, "
		 		+ "cs.created_by as createdBy, cs.created_date as createdDate, cs.player_count as playerCount, "
		 		+ "cs.primary_booking_id as primaryBookingId, cs.secondary_booking as secondaryBooking, cs.slot_available as "
		 		+ "slotAvailable, cs.slot_date as slotDate, cs.slot_end_timestmp as slotEndTimestmp, cs.slot_start_timestmp as slotStartTimeStamp, "
		 		+ "cs.slot_status as slotStatus, cs.tee_time as teeTime from club_slot cs where cs.slot_start_timestmp >=:curDate and cs.slot_end_timestmp <=:stop and cs.tee_time =:teeTime",
		 		
	       nativeQuery = true)
		 List <ClubSlotModel> findBySlotStartTimeStampAfterAndSlotEndTimeStampBefore(Timestamp curDate,Timestamp stop,String teeTime);

//	    @Modifying
//	    @Transactional
//	    @Query(value = "WITH updated AS (" +
//	                   "UPDATE club_slot " +
//	                   "SET slot_status = :slotStatus " +
//	                   "WHERE slot_start_timestmp >= :curDate " +
//	                   "AND slot_start_timestmp <= :stop " +
//	                   "AND tee_time = :teeTime " +
//	                   "RETURNING slot_id, club_name, created_by, created_date, player_count, primary_booking_id, secondary_booking, slot_available, slot_date, slot_end_timestmp, slot_start_timestmp, slot_status, tee_time" +
//	                   ") " +
//	                   "SELECT * FROM updated", 
//	           nativeQuery = true)
//	    List<ClubSlot> updateSlotStatusAndFindUpdated(@Param("slotStatus") String slotStatus, 
//	                                                       @Param("curDate") Timestamp curDate, 
//	                                                       @Param("stop") Timestamp stop, 
//	                                                       @Param("teeTime") String teeTime);
	 @Query(value ="select cs.slot_id as slotId, cs.club_name as clubName, "
		 		+ "cs.created_by as createdBy, cs.created_date as createdDate, cs.player_count as playerCount, "
		 		+ "cs.primary_booking_id as primaryBookingId, cs.secondary_booking as secondaryBooking, cs.slot_available as "
		 		+ "slotAvailable, cs.slot_date as slotDate, cs.slot_end_timestmp as slotEndTimestmp, cs.slot_start_timestmp as slotStartTimeStamp, "
		 		+ "cs.slot_status as slotStatus, cs.tee_time as teeTime from club_slot cs where cs.slot_start_timestmp >=:curDate and cs.slot_Start_timestmp <=:stop and cs.tee_time =:teeTime",
	       nativeQuery = true)
List<ClubSlotModel> findBySlotStartTimeStampGreaterThanEqualAndSlotStartTimeStampLessThanEqual( Timestamp curDate, Timestamp stop,  String teeTime);
//	 @Modifying
//	    @Transactional
//	    @Query(value = "UPDATE club_slot " +
//	                   "SET slot_status = :slotStatus " +
//	                   "WHERE slot_start_timestmp >= :curDate " +
//	                   "AND slot_start_timestmp <= :stop " +
//	                   "AND tee_time = :teeTime", 
//	           nativeQuery = true)
//	 List<ClubSlot> updateSlotStatus(@Param("slotStatus") String slotStatus, 
//	                         @Param("curDate") Timestamp curDate, 
//	                         @Param("stop") Timestamp stop, 
//	                         @Param("teeTime") String teeTime);
	 
	// List <ClubSlotModel> blockSlotsWithinDateRangeAndOnSpecificDays(@Param("curDate") Timestamp curDate,@Param("stop") Timestamp stop,@Param("teeTime") String teeTime);
	//List <ClubSlot> findBySlotStartTimeStampAfterAndSlotEndTimeStampBefore(Timestamp start,Timestamp stop);
	
	
//	 @Query(value = " select * from club_slot cs where cs.slot_start_timestmp >=:curDate and cs.slot_end_timestmp <=:stop and cs.tee_time =:teeTime",
//		 		
//	       nativeQuery = true)
//		 List <ClubSlotModel> findAllBySlotStartTimeStampAfterAndSlotEndTimeStampBefore(Timestamp curDate,Timestamp stop,String teeTime);
	
	ClubSlot findBySlotId(Integer slotId);
	
   

	 @Transactional
	 @Modifying
	 
	 @Query(value = "UPDATE CLUB_SLOT set SECONDARY_BOOKING=:secondaryBooking , SLOT_STATUS = :slotStatus,primary_booking_id  =:primaryBookingId, PLAYER_COUNT =PLAYER_COUNT +:playerCount where SLOT_ID =:slotId",
     nativeQuery = true) 
	 int updateSlotStatus(@Param("slotStatus") String slotStatus,
			 @Param("slotId") Integer slotId, @Param("playerCount") Integer playerCount,@Param("secondaryBooking") String secondaryBooking,@Param("primaryBookingId") Integer primaryBookingId);
	 
	 
	 @Transactional
	 @Modifying
	 
	 @Query(value = "UPDATE CLUB_SLOT set  SLOT_STATUS = :slotStatus where SLOT_ID =:slotId",
     nativeQuery = true) 
	 int updateSlotStatusBySlotId(@Param("slotStatus") String slotStatus, @Param("slotId") Integer slotId);
	 
	 
	 
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE CLUB_SLOT set SLOT_AVAILABLE = :slotAvailable where DATE(SLOT_DATE) = DATE(:slotDate);",
		     nativeQuery = true)
			 int updateSlotAvailability(@Param("slotAvailable") String slotAvailable,
					 @Param("slotDate") String slotDate);
	 
	 
//	 @Modifying
//	 @Transactional
//	 @Query(value = "DELETE FROM club_slot cs WHERE cs.slot_start_timestmp >= :curDate AND cs.slot_start_timestmp <= :stop AND cs.tee_time = :teeTime AND DAYOFWEEK(cs.slot_start_timestmp) IN (:slotDays)", nativeQuery = true)
//	 int deleteSlot(@Param("curDate") Timestamp curDate, @Param("stop") Timestamp stop, @Param("teeTime") String teeTime, @Param("slotDays") List<Integer> slotDays);
//
	 @Modifying
	    @Transactional
	    @Query(value = "DELETE FROM club_slot cs WHERE cs.slot_start_timestmp >= :curDate AND cs.slot_start_timestmp <= :stop AND cs.tee_time = :teeTime AND EXTRACT(DOW FROM slot_start_timestmp) IN (:slotDays)", nativeQuery = true)
	    int deleteSlotsWithinDateRangeAndOnSpecificDays(@Param("curDate") Timestamp curDate, @Param("stop") Timestamp stop,@Param("teeTime") String teeTime,@Param("slotDays") List<Integer> slotDays);

}