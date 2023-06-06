package com.booking.service;

import java.util.List;

import com.booking.model.slot.ClubSlot;

public interface SlotService {
	Iterable<ClubSlot> save(ClubSlot clubSlot);
    
    List<ClubSlot> listBookingSlot();

	List<ClubSlot> findBySlotStatus(String status);
	
	
	int updateSlotAvailability(ClubSlot clubSlot);

	List<ClubSlot> listAllBookingSlot();

	
	
}
