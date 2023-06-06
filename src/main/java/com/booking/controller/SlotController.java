package com.booking.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.config.TokenProvider;
import com.booking.model.user.LoginUser;
import com.booking.model.slot.ClubSlot;
import com.booking.model.user.ClubUser;
import com.booking.model.user.UserDto;
import com.booking.service.ApiHelper;
import com.booking.service.EmailService;
import com.booking.service.SlotService;
import com.booking.service.UserService;
import com.booking.uimodel.ContactUsModel;
import com.booking.uimodel.OTPModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/slot")
public class SlotController {

 @Autowired
 SlotService slotservice;

    

    

   
    @RequestMapping(value = "/addslot", method = RequestMethod.POST)
    public Object AddSlot(@RequestBody ClubSlot clubSlot) {

       
        return slotservice.save(clubSlot);
       
    }
    
    

    
    @RequestMapping(value = "/listbookingslot", method = RequestMethod.POST)
    public Object ListSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.listBookingSlot();
       
    }
    
    
    @RequestMapping(value = "/listallslot", method = RequestMethod.POST)
    public Object ListAllSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.listAllBookingSlot();
       
    }
    
    @RequestMapping(value = "/updateslotavailability", method = RequestMethod.POST)
    public Object updateSlotAvailability(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.updateSlotAvailability(clubSlot);
       
    }
    
    
}
