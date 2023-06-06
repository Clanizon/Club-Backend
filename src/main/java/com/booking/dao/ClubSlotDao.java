package com.booking.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.model.slot.ClubSlot;


@Repository
public interface ClubSlotDao extends CrudRepository<ClubSlot, Long> {

	List <ClubSlot> findBySlotDateBefore(Timestamp slotDate);
	
	List <ClubSlot> findBySlotDateBeforeAndSlotDateAfterAndSlotAvailable(Timestamp slotDate,Timestamp curDate,String slotAvailable);
	
	
	ClubSlot findBySlotId(Integer slotId);
	

	 @Transactional
	 @Modifying
	 
	 @Query(value = "UPDATE CLUB_SLOT set SLOT_STATUS = :slotStatus where SLOT_ID =:slotId",
     nativeQuery = true)
	 int updateSlotStatus(@Param("slotStatus") String slotStatus,
			 @Param("slotId") Integer slotId);
	 
	 
	 @Transactional
	 @Modifying
	 @Query(value = "UPDATE CLUB_SLOT set SLOT_AVAILABLE = :slotAvailable where SLOT_DATE =cast(:slotDate AS timestamp)",
		     nativeQuery = true)
			 int updateSlotAvailability(@Param("slotAvailable") String slotAvailable,
					 @Param("slotDate") String slotDate);
	
}