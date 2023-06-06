package com.booking.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.slot.ClubSlotBooking;
import com.booking.model.user.Roles;

@Repository
public interface ClubSlotBookingDao extends CrudRepository<ClubSlotBooking, Long> {
	
	List<ClubSlotBooking> findByUserId(Integer userId);

}