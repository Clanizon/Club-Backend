package com.booking.service.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.dao.ClubConfigDao;
import com.booking.dao.ClubSlotBookingDao;
import com.booking.dao.ClubSlotDao;
import com.booking.dao.UserBookingDao;
import com.booking.model.SlotBooked;
import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotModel;
import com.booking.model.user.ClubConfig;
import com.booking.service.SlotService;

@Service(value = "SlotService")
public class SlotServiceImpl implements SlotService {

	@Autowired
	private ClubSlotDao slotDao;

	@Autowired
	private ClubSlotBookingDao bookingDao;

	@Autowired
	private ClubConfigDao clubConfigDao;

	@Autowired
	private UserBookingDao userBookingDao;

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Value( "${jpa.datasource.url}" )
	private String dbUrl;
	
	
	@Value( "${spring.datasource.username}" )
	private String userName;
	
	
	@Value( "${spring.datasource.password}" )
	private String password;

	@Override
	@Transactional
	public Map<String, Object> save(ClubSlot clubSlot) {

		// TODO Auto-generated method stub
		Map<String, Object> outputMap = new HashMap<String, Object>();
		List<ClubSlot> slotList = new ArrayList<>();
		// LocalDateTime start = getLocalDateTime(clubSlot.getSlotStartTimeStamp());
		// LocalDateTime stop = getLocalDateTime(clubSlot.getSlotEndTimeStamp());
		
		List<ClubSlotModel> existingSlotList =  slotDao.findBySlotStartTimeStampAfterAndSlotEndTimeStampBefore(clubSlot.getSlotStartTimeStamp(),clubSlot.getSlotEndTimeStamp(),clubSlot.getTeeTime());
		//boolean isOverlap = false;
//		if(existingSlotList!=null && !existingSlotList.isEmpty()) {
//			outputMap.put("Status","Failure");
//			outputMap.put("Message","Slot Record already Exists.Please delete existing slot records and proceed.");
//			outputMap.put("Data",existingSlotList);
//			
//		}
		boolean isOverlap = false;
                                  
		// Get list of days between start and end timestamp
		List<java.util.Date> daysBetween = getDaysBetween(clubSlot.getSlotStartTimeStamp(), clubSlot.getSlotEndTimeStamp());

		// Check for overlapping slots in the database for each day
		for (java.util.Date day : daysBetween) {
		    for (ClubSlotModel dbSlot : existingSlotList) {
		        if (slotsOverlapForDay(clubSlot, dbSlot, day)) {
		            isOverlap = true;
		            break;
		        }
		    }
		    if (isOverlap) break; // Break outer loop if overlap found
		}

		if (isOverlap) {
		    outputMap.put("Status", "Failure");
		    outputMap.put("Message", "Slot Record already Exists. Please delete existing slot records and proceed.");
		    outputMap.put("Data", existingSlotList);
		} 
		else {
        
		LocalDateTime start = clubSlot.getSlotStartTimeStamp().toLocalDateTime();
		LocalDateTime stop = clubSlot.getSlotEndTimeStamp().toLocalDateTime();
		String timeZoneId = "Asia/Kolkata"; // Indian time zone
		ZoneId zoneId = ZoneId.of(timeZoneId);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		//LocalTime startTime = LocalTime.parse(clubSlot.getStart(), formatter);
		//LocalTime endTime = LocalTime.parse(clubSlot.getEnd(), formatter);
		


		List<LocalDateTime> slots = new ArrayList<>();
	//	List<ClubSlot> slotList = new ArrayList<>();
	
		LocalDateTime ldt = start;
		 ZoneId sourceZone = ZoneId.systemDefault();

		LocalTime startTime=start.atZone(sourceZone).withZoneSameInstant(zoneId).toLocalTime();
		LocalTime endTime=stop.atZone(sourceZone).withZoneSameInstant(zoneId).toLocalTime();
		while (ldt.isBefore(stop) || ldt.equals(stop)) {
			System.out.println("-------");
			System.out.println("-------");
			System.out.println("LocalTime");
			System.out.println(ldt.toLocalTime());
			System.out.println("ldt.atZone(zoneId)");
			System.out.println("Stop");
			System.out.println(stop);
			System.out.println("ldt");
			System.out.println(ldt);
			System.out.println(ldt.atZone(zoneId).toLocalTime());
			System.out.println("Start Time");
			System.out.println(startTime);
			System.out.println("-------");
			
		
			slots.add(ldt);
			ClubSlot clubSlots = new ClubSlot();
			clubSlots = SerializationUtils.clone(clubSlot);
			try {
				clubSlots.setSlotStartTimeStamp(getTimestamp(ldt));
			
				
				ZonedDateTime indianDateTime = ldt.atZone(sourceZone).withZoneSameInstant(zoneId);
		        System.out.println("indianDateTime");
		        System.out.println(indianDateTime);
		        System.out.println(Date.valueOf(indianDateTime.toLocalDate()));
				clubSlots.setSlotDate(  Date.valueOf(indianDateTime.toLocalDate()));
				  System.out.println("indianDateTime.toLocalTime");
				System.out.println(indianDateTime.toLocalTime());
				System.out.println(clubSlots.getSlotDate());
		        
			
				
				 if( ( indianDateTime.toLocalTime().isAfter(startTime) ||  
						indianDateTime.toLocalTime().equals(startTime)) && 
						( indianDateTime.toLocalTime().isBefore(endTime) || 
								indianDateTime.toLocalTime().equals(endTime)) ) {
					System.out.println("-TRUEEEEEE-----");     
					ldt = ldt.plusMinutes(clubSlot.getSlotDuration());
					clubSlots.setSlotEndTimeStamp(getTimestamp(ldt));
					System.out.println("ldt.getDayOfWeek().getValue()");
					System.out.println(ldt.getDayOfWeek().getValue());
					if(CollectionUtils.isNotEmpty(clubSlots.getSlotDays()) && clubSlots.getSlotDays().contains(ldt.getDayOfWeek().getValue())) {
					clubSlots.setSlotAvailable("Y");
					slotList.add(clubSlots);
					}
					
				}else {
					System.out.println("-FALSE-----");
					ldt = ldt.plusMinutes(clubSlot.getSlotDuration());
				}	
				
				 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Prepare for the next loop.
		}
	
	
		 saveSlotNative(slotList);
	 
		outputMap.put("Status","Success");
		outputMap.put("Message","Slot Record Added successfully");
		outputMap.put("Data",slotList);
		
		}
		
		return outputMap;
	  }

	private boolean slotsOverlapForDay(ClubSlot slot1, ClubSlotModel slot2, java.util.Date day) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(day);

	    // Get the start and end time for the input slot on the given day
	    cal.set(Calendar.HOUR_OF_DAY, slot1.getSlotStartTimeStamp().getHours());
	    cal.set(Calendar.MINUTE, slot1.getSlotStartTimeStamp().getMinutes());
	    Date slot1Start = new Date(cal.getTime().getTime());

	    cal.set(Calendar.HOUR_OF_DAY, slot1.getSlotEndTimeStamp().getHours());
	    cal.set(Calendar.MINUTE, slot1.getSlotEndTimeStamp().getMinutes());
	    Date slot1End = new Date(cal.getTime().getTime());

	    // Get the start and end time for the existing slot on the given day
	    cal.set(Calendar.HOUR_OF_DAY, slot2.getSlotStartTimeStamp().getHours());
	    cal.set(Calendar.MINUTE, slot2.getSlotStartTimeStamp().getMinutes());
	    Date slot2Start = new Date(cal.getTime().getTime());

	    cal.set(Calendar.HOUR_OF_DAY, slot2.getSlotEndTimeStmp().getHours());
	    cal.set(Calendar.MINUTE, slot2.getSlotEndTimeStmp().getMinutes());
	    Date slot2End = new Date(cal.getTime().getTime());

	    return (slot1Start.before(slot2End) && slot1Start.after(slot2Start)) ||
	           (slot1End.before(slot2End) && slot1End.after(slot2Start)) ||
	           (slot2Start.before(slot1End) && slot2Start.after(slot1Start)) ||
	           (slot2End.before(slot1End) && slot2End.after(slot1Start)) ||
	           (slot1Start.equals(slot2Start)) ||
	           (slot1End.equals(slot2End));
	}

	// Method to get list of days between two dates
	private List<java.util.Date> getDaysBetween(java.util.Date start, java.util.Date end) {
	    List<java.util.Date> dates = new ArrayList<>();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(start);

	    while (cal.getTime().before(end) || cal.getTime().equals(end)) {
	        dates.add(cal.getTime());
	        cal.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
//	@Override
//	public Map<String, Object> vipSlot(ClubSlot clubSlot) {
//		// TODO Auto-generated method stub
//		Map<String, Object> outputMap = new HashMap<String, Object>();
//
//		// LocalDateTime start = getLocalDateTime(clubSlot.getSlotStartTimeStamp());
//		// LocalDateTime stop = getLocalDateTime(clubSlot.getSlotEndTimeStamp());
//		
//		List<ClubSlotModel> existingSlotList =  slotDao.findBySlotStartTimeStampAfterAndSlotEndTimeStampBefore(clubSlot.getSlotStartTimeStamp(),clubSlot.getSlotEndTimeStamp(),clubSlot.getTeeTime());
//		
//		if(existingSlotList!=null && !existingSlotList.isEmpty()) {
//			outputMap.put("Status","Failure");
//			outputMap.put("Message","Slot Record already Exists.Please delete existing slot records and proceed.");
//			outputMap.put("Data",existingSlotList);
//		}
//		else {
//     
//		LocalDateTime start = clubSlot.getSlotStartTimeStamp().toLocalDateTime();
//		LocalDateTime stop = clubSlot.getSlotEndTimeStamp().toLocalDateTime();
//		String timeZoneId = "Asia/Kolkata"; // Indian time zone
//		ZoneId zoneId = ZoneId.of(timeZoneId);
//		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//		//LocalTime startTime = LocalTime.parse(clubSlot.getStart(), formatter);
//		//LocalTime endTime = LocalTime.parse(clubSlot.getEnd(), formatter);
//		
//
//
//		List<LocalDateTime> slots = new ArrayList<>();
//		List<ClubSlot> slotList = new ArrayList<>();
//	
//		LocalDateTime ldt = start;
//		 ZoneId sourceZone = ZoneId.systemDefault();
//
//		LocalTime startTime=start.atZone(sourceZone).withZoneSameInstant(zoneId).toLocalTime();
//		LocalTime endTime=stop.atZone(sourceZone).withZoneSameInstant(zoneId).toLocalTime();
//		while (ldt.isBefore(stop)) {
//			System.out.println("-------");
//			System.out.println("-------");
//			System.out.println("LocalTime");
//			System.out.println(ldt.toLocalTime());
//			System.out.println("ldt.atZone(zoneId)");
//			System.out.println("Stop");
//			System.out.println(stop);
//			System.out.println("ldt");
//			System.out.println(ldt);
//			System.out.println(ldt.atZone(zoneId).toLocalTime());
//			System.out.println("Start Time");
//			System.out.println(startTime);
//			System.out.println("-------");
//			
//		
//			slots.add(ldt);
//			ClubSlot clubSlots = new ClubSlot();
//			clubSlots = SerializationUtils.clone(clubSlot);
//			try {
//				clubSlots.setSlotStartTimeStamp(getTimestamp(ldt));
//			
//				
//				ZonedDateTime indianDateTime = ldt.atZone(sourceZone).withZoneSameInstant(zoneId);
//		        System.out.println("indianDateTime");
//		        System.out.println(indianDateTime);
//		        System.out.println(Date.valueOf(indianDateTime.toLocalDate()));
//				clubSlots.setSlotDate(  Date.valueOf(indianDateTime.toLocalDate()));
//				  System.out.println("indianDateTime.toLocalTime");
//				System.out.println(indianDateTime.toLocalTime());
//				System.out.println(clubSlots.getSlotDate());
//		        
//			
//				
//				 if( ( indianDateTime.toLocalTime().isAfter(startTime) ||  
//						indianDateTime.toLocalTime().equals(startTime))  && 
//						( indianDateTime.toLocalTime().isBefore(endTime) || 
//								indianDateTime.toLocalTime().equals(endTime)) ) {
//					System.out.println("-TRUEEEEEE-----");
//					ldt = ldt.plusMinutes(clubSlot.getSlotDuration());
//					clubSlots.setSlotEndTimeStamp(getTimestamp(ldt));
//					System.out.println("ldt.getDayOfWeek().getValue()");
//					System.out.println(ldt.getDayOfWeek().getValue());
//					if(CollectionUtils.isNotEmpty(clubSlots.getSlotDays()) && clubSlots.getSlotDays().contains(ldt.getDayOfWeek().getValue())) {
//					clubSlots.setSlotAvailable("Y");
//					clubSlots.setSlotStatus("Blocked for VIP");
//					slotList.add(clubSlots);
//					}
//					
//				}else {
//					System.out.println("-FALSE-----");
//					ldt = ldt.plusMinutes(clubSlot.getSlotDuration());
//				}	
//				
//				 
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// Prepare for the next loop.
//		}
//	
//		saveSlotNative(slotList);
//	 
//		outputMap.put("Status","Success");
//		outputMap.put("Message","Slot Record Added successfully");
//		outputMap.put("Data",slotList);
//		
//		}
//	
//		return outputMap;
//	}
	
	@Override
	public Map<String, Object> vipSlot(ClubSlot clubSlot) {
		// TODO Auto-generated method stub
		Map<String, Object> outputMap = new HashMap<String, Object>();
		
			List<ClubSlotModel> existingSlotList =  slotDao.findBySlotStartTimeStampGreaterThanEqualAndSlotStartTimeStampLessThanEqual( clubSlot.getSlotStartTimeStamp(),clubSlot.getSlotEndTimeStamp(),clubSlot.getTeeTime());

			   boolean slotBlockedForVIP = false;

			    if (existingSlotList != null && !existingSlotList.isEmpty()) {
			        for (ClubSlotModel slot : existingSlotList) {
			            // Check if the slot has already been blocked for VIP
			            if ("Blocked for VIP".equals(slot.getSlotStatus())) {
			                slotBlockedForVIP = true;
			                break;  // No need to continue checking other slots
			            }
			        }

			        if (slotBlockedForVIP) {
			            outputMap.put("Status", "Failure");
			            outputMap.put("Message", "Already slot has been blocked");
			        } else {
			            // Update each slot individually
			            for (ClubSlotModel slot : existingSlotList) {
			                slotDao.updateSlotStatusBySlotId(clubSlot.getSlotStatus(), slot.getSlotId());
			            }
			            outputMap.put("Status", "Success");
			            outputMap.put("Message", "Existing Slot Record Blocked successfully");
			            outputMap.put("Data", existingSlotList);
			        }
			    } else {
			        outputMap.put("Status", "Failure");
			        outputMap.put("Message", "There is no open slots to be blocked for the given range");
			    }

			    return outputMap;
}

	
	private void saveSlotNative(List<ClubSlot> slotList) {
		
		Connection conn = null;
		
		 try {	
			Class.forName("org.postgresql.Driver");
			conn = DriverManager
			            .getConnection("jdbc:postgresql://database-1.cnaee60qc6yl.us-east-2.rds.amazonaws.com/clubdb",
			            		userName, password);
//			conn = DriverManager
//		            .getConnection("jdbc:postgresql://localhost:5432/Clubdb",
//		            		userName, password);
			  conn.setAutoCommit(false);
				//String slotSaveSQl= "INSERT INTO public.club_slot( club_name) 	VALUES ( ?)";

				String slotSaveSQl= "INSERT INTO public.club_slot( club_name, created_by, created_date, secondary_booking, slot_date, slot_end_timestmp, slot_start_timestmp, "
						+ "slot_status, slot_available, player_count, primary_booking_id, tee_time) 	VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(slotSaveSQl);

				   
				   slotList.forEach(slot->{
					   try {
						stmt.setString(1, slot.getClubName());
						stmt.setString(2, slot.getCreatedBy());
						stmt.setTimestamp(3, slot.getCreatedDate());
						stmt.setString(4, slot.getSecondaryBooking());
						stmt.setDate(5, slot.getSlotDate());
						stmt.setTimestamp(6, slot.getSlotEndTimeStamp());
						stmt.setTimestamp(7, slot.getSlotStartTimeStamp());
						stmt.setString(8, slot.getSlotStatus());
						stmt.setString(9, slot.getSlotAvailable());
						stmt.setInt(10, slot.getPlayerCount());
						if(slot.getPrimaryBookingId()!=null) {
							stmt.setInt(11, slot.getPrimaryBookingId());
						}else {
							stmt.setInt(11, 0);
						}
						stmt.setString(12, slot.getTeeTime());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					   try {
						   stmt.addBatch();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   
				   });
				   
				   stmt.executeBatch();
				   conn.commit();
				   conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      


			
		
	}

	@Override
	public List<ClubSlot> listBookingSlot() {
		// TODO Auto-generated method stub

		System.out.println("LocalDateTime.now()");
		System.out.println(LocalDateTime.now());
		String timeZoneId = "Asia/Kolkata"; // Indian time zone
		ZoneId zoneId = ZoneId.of(timeZoneId);

		String starttime = "5:45:00";
		
		
		

	

		ClubConfig clubconfigtime = clubConfigDao.findByKey("starttime");
		if (clubconfigtime != null) {
			starttime = clubconfigtime.getValue();
		}
		
		String starttimeDisplay=starttime.split(":")[0] + ":"+starttime.split(":")[1];

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime startDateTime = LocalTime.parse(starttime, formatter);
		int duration = 2;
		ClubConfig config = clubConfigDao.findByKey("duration");
		if (config != null && config.getValue() != null) {
			duration = Integer.parseInt(config.getValue());
		}
		
		LocalDate currentdZonedDate = LocalDate.now(zoneId);
		
		if (LocalTime.now(zoneId).isBefore(startDateTime)) {
			duration = duration - 1;
		} else {

		}
		
		
		//Past Slot Never Display 
	    //
		
		LocalDate currentdnonZonedDate = LocalDate.now();
		Timestamp timestampnow = Timestamp.valueOf(currentdnonZonedDate.atTime(LocalTime.now())); 
	
		LocalDate futureZonedDate = currentdZonedDate.plusDays(duration);

		Timestamp futureTimeStamp = Timestamp.valueOf(futureZonedDate.atTime(18,29, 59));
		
		//03-07-2023 
		//04-07-2023 18:30:30 UTC ->IST 4-7-2023 ,23::59:59
		
		//04-07-2023 18:30:30 05-07-2023 18:30:30 -0
		
		//05-07-2023 //Error Message 
		
		
		 LocalTime time1
         = LocalTime.parse(clubconfigtime.getValue());

        DateTimeFormatter formatterdisp = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		List<ClubSlot> slotList = slotDao
				.findBySlotStartTimeStampAfterAndSlotAvailableAndSlotStatusNotOrderBySlotStartTimeStampAsc(timestampnow, "Y","Blocked for VIP");
		slotList.forEach(slot -> {
			if (slot.getSlotStartTimeStamp().after(futureTimeStamp)) { //Success only 04-07-2023 18:30:30 UTC
		
			long diffdays =findDiffDays(futureTimeStamp,slot.getSlotStartTimeStamp()); //Future Always depends on config
		
			  LocalDate futureDateCutofftime;
			  
			  
			   //TBD Remove this and Make it as Indian Date Handling
			    String cuttime = "05:30:00";
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalTime startDateTime1 = LocalTime.parse(cuttime, formatter1);
			  
				System.out.println(startDateTime1);
				System.out.println("startDateTime1");
				System.out.println(LocalTime.now(zoneId));
			
			  
			  if(startDateTime1.compareTo(LocalTime.now(zoneId)) <0 ){
				  
				  if(time1.compareTo(LocalTime.now(zoneId)) > 0){
					  futureDateCutofftime = currentdnonZonedDate.plusDays(diffdays);
				  }else {
					  futureDateCutofftime = currentdnonZonedDate.plusDays(diffdays+1);
				  }
				  

			  }else {
				  if(time1.compareTo(LocalTime.now(zoneId)) > 0){
					  futureDateCutofftime = currentdnonZonedDate.plusDays(diffdays+1);
				  }else {
					  futureDateCutofftime = currentdnonZonedDate.plusDays(diffdays+2);
				  }
				  
				  
				  
      	  }
			slot.setBookingMessage("Booking starts at "+futureDateCutofftime.format(formatterdisp)+" Time "+starttimeDisplay);
			slot.setSlotStarted("N");	 //100 % 
	
			}
		});
		return slotList;
	}

	public long findDiffDays(Timestamp curtime, Timestamp oldtime) {
		long milliseconds1 = curtime.getTime();
		long milliseconds2 = oldtime.getTime();
		long diff = milliseconds2 - milliseconds1;

		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	@Override
	public Map<String, Object> listAllBookingSlot() {
		
		LocalDate currentdnonZonedDate = LocalDate.now();
		Timestamp timestampnow = Timestamp.valueOf(currentdnonZonedDate.atTime(LocalTime.now())); 
		List<ClubSlotModel> slotList= (List<ClubSlotModel>)  slotDao.findAdminSlot(timestampnow);

		Map<String, Object> outputMap= new HashMap<>();
		outputMap.put("slotList", slotList);
		outputMap.put("slotAvalaibity", convertListBeforeJava8(slotList));
	    return outputMap;
	}

	public Timestamp getTimestamp(LocalDateTime localDateTime) throws ParseException {
		String timeZoneId = "Asia/Kolkata"; // Indian time zone
	

		ZoneId zoneId = ZoneId.of(timeZoneId);
		ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
		System.out.println("zonedDateTime.getDayOfWeek()");
		Timestamp timestamp = Timestamp.valueOf(zonedDateTime.toLocalDateTime());
		System.out.println("timestamp.getDayOfWeek()");
		
		return timestamp;
//		  String timeZoneId = "Asia/Kolkata"; // Indian time zone
//
//		    ZoneId zoneId = ZoneId.of(timeZoneId);
//		    ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
//
//		    // Truncate the LocalDateTime to remove seconds and nanoseconds
//		    LocalDateTime truncatedLocalDateTime = zonedDateTime.toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
//		    
//		    Timestamp timestamp = Timestamp.valueOf(truncatedLocalDateTime);
//
//		    return timestamp;
	}

	public LocalDateTime getLocalDateTime(Timestamp timestamp) {
		String timeZoneId = "Asia/Kolkata"; // Example time zone (you can replace it with your desired time zone)

		ZoneId zoneId = ZoneId.of(timeZoneId);
		ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(zoneId);
		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
		return localDateTime;
	}

	
	
	 public LinkedHashMap<String, String> convertListBeforeJava8(List<ClubSlotModel> list) {
		    LinkedHashMap<String, String> map = new LinkedHashMap<>();
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		    
		    for (ClubSlotModel slot : list) {
		    	String strDate = dateFormat.format(slot.getSlotDate()); 
		    	
		        map.put(strDate, slot.getSlotAvailable());
		    }
		    return map;
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

	@Override
	public Object deleteSlot(Integer slotId) {
		// TODO Auto-generated method stub
		bookingDao.updateApprovalStatus("Slot Cancelled", slotId);
		userBookingDao.updateApprovalStatus("Slot Cancelled", slotId);
		slotDao.deleteById(slotId);
		return "Slot Successfully Cancelled";

	}


	@Override
	public Map<String, Object> delete(ClubSlot clubSlot) {
		// TODO Auto-generated method stub
		Map<String, Object> outputMap = new HashMap<String, Object>();
		
		SlotBooked slotBooked = userBookingDao.findBySlotRangeandTeeTime(clubSlot.getSlotStartTimeStamp(),clubSlot.getSlotEndTimeStamp(),clubSlot.getTeeTime());
		
		 if(slotBooked.getSlotBooked().equals(0)){
			int deletecount =  slotDao.deleteSlotsWithinDateRangeAndOnSpecificDays(clubSlot.getSlotStartTimeStamp(),clubSlot.getSlotEndTimeStamp(),clubSlot.getTeeTime(),clubSlot.getSlotDays());
			
			outputMap.put("Status", "Success");
			outputMap.put("Message", "Record Deleted Successfully");
			outputMap.put("Data", slotBooked);
			System.out.println("****************************************"+deletecount);
			
		}
			
		else {
			outputMap.put("Status", "Failure");
			outputMap.put("Message", "Booking Exists for the Slot.please Delete Booking and delete slot");
		}
		
	    
 
		
		return outputMap;
	}


	@Override
	public Map<String, Object> individuaSlotBlock(ClubSlot clubSlot) {
		Map<String, Object> outputMap = new HashMap<String, Object>();
	int updatedSlot =	slotDao.updateSlotStatusBySlotId(clubSlot.getSlotStatus(), clubSlot.getSlotId());
		outputMap.put("Status", "Success");
		outputMap.put("Message", "Successfully Slot blocked for VIP");
		outputMap.put("Data", updatedSlot);
		return outputMap;
	}

	@Override
	public Map<String, Object> individuaSlotUnBlock(ClubSlot clubSlot) {
		Map<String, Object> outputMap = new HashMap<String, Object>();
		int updatedSlot =	slotDao.updateSlotStatusBySlotId(clubSlot.getSlotStatus(), clubSlot.getSlotId());
			outputMap.put("Status", "Success");
			outputMap.put("Message", "Successfully VIP slot has been unblocked");
			outputMap.put("Data", updatedSlot);
			return outputMap;
	}


}
