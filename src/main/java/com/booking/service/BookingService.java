package com.booking.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.booking.model.slot.ClubSlotBooking;
import com.booking.model.slot.UserBooking;

public interface BookingService {
    Object save(ClubSlotBooking clubSlotBooking);
    
    List<ClubSlotBooking> findAll(Date slotDate);

	List<ClubSlotBooking> findByUserId(Integer userId);

	 List<ClubSlotBooking> findBySlotDate(Date timestamp);

	List<UserBooking> findByUserBooking(Integer userId);
	
	List<UserBooking> findByBookingId(Integer bookingid);
	
	Object deleteBooking(ClubSlotBooking bookingId);

	Object deleteUserBooking(ClubSlotBooking bookingId);
	
	Object handleApproval(ClubSlotBooking booking);

	Object listapprovallist(ClubSlotBooking clubSlot);

	Object updateBooking(ClubSlotBooking clubSlot);

	Object listActiveBooking(Date slotDate);
	
	
	
}
