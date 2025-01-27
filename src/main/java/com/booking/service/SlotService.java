package com.booking.service;

import java.util.List;
import java.util.Map;

import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.HoldRequest;

public interface SlotService {
	Map<String, Object> save(ClubSlot clubSlot);
	
	Map<String, Object> vipSlot(ClubSlot clubSlot);
    
	Map<String, Object> individuaSlotBlock(ClubSlot clubSlot);
	
	Map<String, Object> individuaSlotUnBlock(ClubSlot clubSlot);
	
    List<ClubSlot> listBookingSlot();

	List<ClubSlot> findBySlotStatus(String status);
	
	Object deleteSlot(Integer slotId);
	int updateSlotAvailability(ClubSlot clubSlot);

	Map<String, Object> listAllBookingSlot();

	Map<String, Object> delete(ClubSlot clubSlot);

	Map<String, Object> updateSlotStatus(HoldRequest clubSlot);
	
}
