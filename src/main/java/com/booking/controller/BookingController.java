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

		return bookingservice.findAll();

	}

	@RequestMapping(value = "/listuserbooking", method = RequestMethod.POST)
	public Object listuserbooking(@RequestBody ClubSlotBooking clubSlot) {

		return bookingservice.findByUserId(clubSlot.getUserId());
		// return bookingservice.findAll();

	}

}
