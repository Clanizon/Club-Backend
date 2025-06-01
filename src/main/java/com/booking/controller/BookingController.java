package com.booking.controller;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.slot.ClubSlotBooking;
import com.booking.service.BookingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingservice;
	
	
	private static final ConcurrentHashMap<Integer, Object> slotLocks = new ConcurrentHashMap<>();
	
    // Thread-safe map to store per-slot locks
    public BookingController(BookingService bookingservice) {
        this.bookingservice = bookingservice;
    }
        @RequestMapping(value = "/createbooking", method = RequestMethod.POST)
        public Object addSlot(@RequestBody ClubSlotBooking booking) {
            Integer slotId = booking.getSlotId();
            // Atomically get or create a lock object for the slot
            Object lock = slotLocks.computeIfAbsent(slotId, id -> new Object());
            synchronized (lock) {
                try {
                    return bookingservice.save(booking);
                } finally {
                  //  slotLocks.remove(slotId, lock); // Only if no one else is waiting — tricky
                    // Optional: lock cleanup logic
                    // Consider adding cleanup only if no other threads are waiting
                    // For simplicity, you might accept slight memory growth
                }
            }
        }
   
	
	@RequestMapping(value = "/createbookingold", method = RequestMethod.POST)
    public Object addSlot1(@RequestBody ClubSlotBooking booking) {
        Integer slotId = booking.getSlotId();
        // Atomically get or create a lock object for the slot
        Object lock = slotLocks.computeIfAbsent(slotId, id -> new Object());
        synchronized (lock) {
            try {
                return bookingservice.save(booking);
            } finally {
                // Optional: lock cleanup logic
                // Consider adding cleanup only if no other threads are waiting
                // For simplicity, you might accept slight memory growth
            }
        }
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
