package com.booking.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.ClubSlotBookingDao;
import com.booking.dao.ClubSlotDao;
import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotBooking;
import com.booking.service.BookingService;
import com.booking.service.RoleService;
import com.booking.service.SlotService;

@Service(value = "SlotService")
public class SlotServiceImpl implements SlotService {


    @Autowired
    private ClubSlotDao slotDao;
    


	 @PersistenceContext
	 private EntityManager entityManager;

 

   

	@Override
	public Iterable<ClubSlot> save(ClubSlot clubSlot) {
		
		// TODO Auto-generated method stub
		
		LocalDateTime start = getLocalDateTime(clubSlot.getSlotStartTimeStamp());
		LocalDateTime stop = getLocalDateTime(clubSlot.getSlotEndTimeStamp());
    	
    	List< LocalDateTime > slots = new ArrayList<>() ;
    	List< ClubSlot > slotList = new ArrayList<>() ;
    	LocalDateTime ldt = start ;
    	while (
    	    ldt.isBefore( stop ) 
    	) {
    	    slots.add( ldt ) ;
    	    ClubSlot clubSlots= new ClubSlot();
    	    clubSlots= SerializationUtils.clone(clubSlot);
    		try {
				clubSlots.setSlotStartTimeStamp(getTimestamp(ldt));
				Integer slot= 8;    
				slot=clubSlot.getSlotDuration();
				
    		ldt = ldt.plusMinutes(clubSlot.getSlotDuration());
    		clubSlots.setSlotEndTimeStamp(getTimestamp(ldt));
    		clubSlots.setSlotAvailable("Y");
    		slotList.add(clubSlots);
    		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    // Prepare for the next loop. 
    	   
    	}
    	slotList.forEach(s->{
    		System.out.println("-------");
    		System.out.println(s.getSlotStartTimeStamp());
    		System.out.println(s.getSlotEndTimeStamp());
    		System.out.println("-------");
    	});
		return slotDao.saveAll(slotList);
	}


	@Override
	public List<ClubSlot> listBookingSlot() {
		// TODO Auto-generated method stub
		  LocalDate currentdDate1 =  LocalDate.now();
	        LocalDate currentDatePlus1 = currentdDate1.plusDays(1);
	        
	        Timestamp timestamp = Timestamp.valueOf(currentDatePlus1.atStartOfDay());
	        
	        Timestamp timestampnow = Timestamp.valueOf(currentdDate1.atStartOfDay());
		
	 

		return slotDao.findBySlotDateBeforeAndSlotDateAfterAndSlotAvailable(timestamp,timestampnow,"Y");
	}
	
	@Override
	public List<ClubSlot> listAllBookingSlot() {
	
		
	 

		return (List<ClubSlot>) slotDao.findAll();
	}

    
    public Timestamp getTimestamp(LocalDateTime localDateTime) throws ParseException {
	String timeZoneId = "Asia/Kolkata"; // Indian time zone
    System.out.println(localDateTime);
	
    ZoneId zoneId = ZoneId.of(timeZoneId);
    ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
    Timestamp timestamp = Timestamp.valueOf(zonedDateTime.toLocalDateTime());

    return timestamp;
    }
    
    public LocalDateTime getLocalDateTime(Timestamp timestamp) {
    	String timeZoneId = "Asia/Kolkata"; // Example time zone (you can replace it with your desired time zone)

        ZoneId zoneId = ZoneId.of(timeZoneId);
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(zoneId);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
		return localDateTime;
    }



	@Override
	public List<ClubSlot> findBySlotStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int updateSlotAvailability(ClubSlot clubSlot) {
		// TODO Auto-generated method stub
		return slotDao.updateSlotAvailability(clubSlot.getSlotAvailable(), clubSlot.getSlotDate().toString());
	}

	
	

	
}
