package com.booking.service;

import java.util.List;

import com.booking.model.slot.ClubSlotBooking;

public interface BookingService {
    Object save(ClubSlotBooking clubSlotBooking);
    
    List<ClubSlotBooking> findAll();

	List<ClubSlotBooking> findByUserId(Integer userId);

	
	
}
