package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotBooking;
import com.booking.service.BookingService;
import com.booking.service.SlotService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingservice;

	@RequestMapping(value = "/createbooking", method = RequestMethod.POST)
	public Object AddSlot(@RequestBody ClubSlotBooking booking) {

		return bookingservice.save(booking);

	}

	@RequestMapping(value = "/listbookedslot", method = RequestMethod.POST)
	public Object ListSlot(@RequestBody ClubSlotBooking clubSlot) throws AuthenticationException {

		return bookingservice.findAll(clubSlot.getSlotDate());

	}

	@RequestMapping(value = "/listuserbooking", method = RequestMethod.POST)
	public Object listuserbooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.findByUserBooking(clubSlot.getUserId());
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/listuserbybookingid", method = RequestMethod.POST)
	public Object listuserbybookingid(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.findByBookingId(clubSlot.getBookingId());
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/listbookingbyslotdate", method = RequestMethod.POST)
	public Object listbookingbyslotdate(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.findBySlotDate(clubSlot.getSlotDate());
		// return bookingservice.findAll();

	}
	
	
	@RequestMapping(value = "/listactivebooking", method = RequestMethod.POST)
	public Object listActiveBooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.listActiveBooking(clubSlot.getSlotDate());
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/listuserbookingnew", method = RequestMethod.POST)
	public Object listuserbookingnew(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.findByUserBooking(clubSlot.getUserId());
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/deletebooking", method = RequestMethod.POST)
	public Object deletebooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.deleteBooking(clubSlot);
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/DeleteUserBooking", method = RequestMethod.POST)
	public Object deleteUserbooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.deleteUserBooking(clubSlot);
		// return bookingservice.findAll();

	}
	
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public Object updateBooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.updateBooking(clubSlot);
		// return bookingservice.findAll();

	}
	
	

	@RequestMapping(value = "/bookingupdate", method = RequestMethod.POST)
	public Object approveBooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.handleApproval(clubSlot);
		// return bookingservice.findAll();

	}
	
	@RequestMapping(value = "/listapprovallist", method = RequestMethod.POST)
	public Object listapprovallist(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.listapprovallist(clubSlot);
		// return bookingservice.findAll();

	}

}
