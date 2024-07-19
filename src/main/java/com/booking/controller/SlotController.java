package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.slot.ClubSlot;
import com.booking.service.SlotService;

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
    
    @RequestMapping(value = "/blockslot", method = RequestMethod.POST)
    public Object blockSlot(@RequestBody ClubSlot clubSlot) {

       
        return slotservice.vipSlot(clubSlot);
       
    }
    
    @RequestMapping(value = "/BlockIndividualSlot", method = RequestMethod.POST)
    public Object blockIndividualSlot(@RequestBody ClubSlot clubSlot) {

       
        return slotservice.individuaSlotBlock(clubSlot);
       
    }
    
    @RequestMapping(value = "/UnBlockIndividualSlot", method = RequestMethod.POST)
    public Object unblockIndividualSlot(@RequestBody ClubSlot clubSlot) {

       
        return slotservice.individuaSlotUnBlock(clubSlot);
       
    }
    
    @RequestMapping(value = "/deleteslotrange", method = RequestMethod.POST)
    public Object DeleteSlot(@RequestBody ClubSlot clubSlot) {

       
        return slotservice.delete(clubSlot);
       
    }
    
    

    
    @RequestMapping(value = "/listbookingslot", method = RequestMethod.POST)
    public Object ListSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.listBookingSlot();
       
    }
    
    
    @RequestMapping(value = "/listallslot", method = RequestMethod.POST)
    public Object ListAllSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.listAllBookingSlot();
       
    }
    
    
    @RequestMapping(value = "/deleteslot", method = RequestMethod.POST)
    public Object deleteSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.deleteSlot(clubSlot.getSlotId());
       
    }
    
    
    @RequestMapping(value = "/deleterangeslot", method = RequestMethod.POST)
    public Object deleteRangeSlot(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.deleteSlot(clubSlot.getSlotId());
       
    }
    
    @RequestMapping(value = "/updateslotavailability", method = RequestMethod.POST)
    public Object updateSlotAvailability(@RequestBody ClubSlot clubSlot) throws AuthenticationException {

       
        return slotservice.updateSlotAvailability(clubSlot);
       
    }
    
    
}
