package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dao.ClubConfigDao;
import com.booking.model.slot.ClubSlotBooking;
import com.booking.model.user.ClubConfig;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	ClubConfigDao clubConfigDao;

	@RequestMapping(value = "/saveconfig", method = RequestMethod.POST)
	public Object AddSlot(@RequestBody ClubConfig booking) {

		return clubConfigDao.save(booking);

	}

	
	
	@RequestMapping(value = "/fetchconfig", method = RequestMethod.POST)
	public Object listbookingbyslotdate(@RequestBody ClubSlotBooking clubSlot) {

		return clubConfigDao.findAll();
		// return bookingservice.findAll();

	}

}
