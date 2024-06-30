package com.booking.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.ClubSlotBookingDao;
import com.booking.dao.ClubSlotDao;
import com.booking.dao.UserBookingDao;
import com.booking.dao.UserDao;
import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotBooking;
import com.booking.model.slot.UserBooking;
import com.booking.model.user.ClubUser;
import com.booking.service.BookingService;
import com.booking.uimodel.UIResponse;


@Service(value = "BookingService")
public class BookingServiceImpl implements BookingService {



    @Autowired
    private ClubSlotBookingDao slotBookingDao;
    


	 @PersistenceContext
	 private EntityManager entityManager;
	 
	  @Autowired
	  private ClubSlotDao slotDao;
	  
	  @Autowired
	  private UserDao userDao;
	  
	  @Autowired
	  private UserBookingDao userbookDao;

 

   

		@SuppressWarnings("unused")
		@Override
		public Object save(ClubSlotBooking clubSlotBooking) {
			// TODO Auto-generated method stub
			ClubSlotBooking newbooking = new ClubSlotBooking();
			UIResponse uiResponse = new UIResponse();
			ClubSlot clubSlot = slotDao.findBySlotId(clubSlotBooking.getSlotId());
			
			List<UserBooking> userList= new ArrayList<UserBooking>();
			if (clubSlot != null)
			{
				clubSlotBooking.setSlotDate(clubSlot.getSlotDate());
								if(clubSlot.getSlotStatus().equals("Created")) {
									    if(clubSlotBooking.getBookingType().equals("Primary"))
									    {
													newbooking = slotBookingDao.save(clubSlotBooking);
													int id=newbooking.getBookingId();
													
													if(newbooking!= null ) {
														newbooking.getUserBooking().forEach(user->{
															user.setBookingId(id);
														
															userList.add(user);
															
														});
														userbookDao.saveAll(userList);
													}
													if(clubSlotBooking.getPlayerCount().equals(4) || clubSlotBooking.getSecondaryBooking().equals("N")) {
														slotDao.updateSlotStatus("Secondary Booked", clubSlotBooking.getSlotId(),clubSlotBooking.getPlayerCount(),clubSlotBooking.getSecondaryBooking(),clubSlotBooking.getPrimaryBookingId());
													}else {
														slotDao.updateSlotStatus("Primary Booked", clubSlotBooking.getSlotId(),clubSlotBooking.getPlayerCount(),clubSlotBooking.getSecondaryBooking(),clubSlotBooking.getPrimaryBookingId());

													}
													newbooking.setClubSlot(clubSlot);
											    	uiResponse.setStatus("SUCCESS");
											    	uiResponse.setStatusMessage("Primary Booking Created Successfuly");
											    	uiResponse.setResponse(newbooking);
									    }
									    else {
									    	uiResponse.setStatus("Failure");
									    	uiResponse.setStatusMessage("Primary Booking not AVailable");
									    	uiResponse.setResponse("Failure");
									    }
						     }else if(clubSlot.getSlotStatus().equals("Primary Booked")){
						    	 if(clubSlotBooking.getBookingType().equals("Secondary"))
								    {

												newbooking = slotBookingDao.save(clubSlotBooking);
												int id=newbooking.getBookingId();
												if(newbooking!= null ) {
													newbooking.getUserBooking().forEach(user->{
														user.setBookingId(id);
													
														userList.add(user);
														
													});
													userbookDao.saveAll(userList);
												}
												
												if(newbooking.getSlotId()!=null) {
													newbooking.setClubSlot(clubSlot);
												}
												slotDao.updateSlotStatus("Secondary Booked", clubSlotBooking.getSlotId(),clubSlotBooking.getPlayerCount(),"N",clubSlotBooking.getPrimaryBookingId());
												
										    	uiResponse.setStatus("SUCCESS");
										    	uiResponse.setStatusMessage("Secondary Booking Created Successfuly");
										    	uiResponse.setResponse(newbooking);
								    }else {
								    	uiResponse.setStatus("Failure");
								    	uiResponse.setStatusMessage("Primary Booking already Done");
								    	uiResponse.setResponse("Failure");
						    	 
						    	 
								    }
						     }
						    	 
				
			}else {
				uiResponse.setStatus("Failure");
		    	uiResponse.setStatusMessage("Invalid Slot Id");
		    	uiResponse.setResponse("Failure");
			}
			return uiResponse;
		}




		@Override
		public List<ClubSlotBooking> findAll() {
			// TODO Auto-generated method stub
			List<ClubSlotBooking> slotBookingList = (List<ClubSlotBooking>) slotBookingDao.findAll();
			if (slotBookingList != null)
				slotBookingList.forEach(slb -> {
					if (slb.getSlotId() != null) {
						ClubSlot slotDetail = new ClubSlot();
						slotDetail = slotDao.findBySlotId(slb.getSlotId());
						slb.setClubSlot(slotDetail);
					}
					if (slb.getUserId() != null) {
            
						
						ClubUser clubuser = new ClubUser();
						clubuser = userDao.findByUserId(slb.getUserId());
						slb.setClubUser(clubuser);
					}

				});

List<ClubSlotBooking> res = slotBookingList.stream().filter(filterlsit ->filterlsit.getClubSlot()!=null)      
                    .sorted(Comparator.comparing(fo->fo.getClubSlot().getSlotStartTimeStamp()))
                    .collect(Collectors.toList());
			return res;
		}
	
	
	@Override
	public List<ClubSlotBooking> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ClubSlotBooking> slotBookingList = (List<ClubSlotBooking>) slotBookingDao.findByUserId(userId);
		if(slotBookingList!=null)
		slotBookingList.forEach(slb->{
			if(slb.getSlotId()!=null) {
				ClubSlot slotDetail = new ClubSlot();
				slotDetail=slotDao.findBySlotId(slb.getSlotId());
				slb.setClubSlot(slotDetail);
			}
			
			
		});
		return slotBookingList;
	}
	
	@Override
	public Object handleApproval(ClubSlotBooking booking) {
		// TODO Auto-generated method stub
		slotBookingDao.updateApprovalStatus(booking.getBookingStatus(),booking.getBookingId());
		userbookDao.updateApprovalStatus(booking.getBookingStatus(),booking.getBookingId());
		
		if(booking!=null && booking.getBookingStatus().equals("Approved")) {
			
			
		}else {
			slotDao.updateSlotStatus("Primary Booked", booking.getSlotId(),-(booking.getPlayerCount()),booking.getSecondaryBooking(),booking.getPrimaryBookingId());
		}
		
		return "Booking Approval Updated Successfully";
		

		
	}
	
	
	@Override
	public List<UserBooking> findByUserBooking(Integer userId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserBooking> slotBookingList =  userbookDao.findByUserId(userId);
		if(slotBookingList!=null)
		slotBookingList.forEach(slb->{
			if(slb.getSlotId()!=null) {	
				ClubSlot slotDetail = new ClubSlot();
				slotDetail=slotDao.findBySlotId(slb.getSlotId());
				System.out.println(slotDetail);
				slb.setClubSlot(slotDetail);
			}
			
			
		});
		return slotBookingList;
	}




	@Override
	public List<ClubSlotBooking> findBySlotDate(Date slotDate) {
		// TODO Auto-generated method stub
		return slotBookingDao.findBySlotDate(slotDate);
	}




	@Override
	public Object deleteBooking(ClubSlotBooking clubSlot) {
		// TODO Auto-generated method stub
		 slotBookingDao.updateApprovalStatus("Booking Cancelled",clubSlot.getBookingId());
		userbookDao.updateApprovalStatus("Booking Cancelled",clubSlot.getBookingId());
		
		if(clubSlot.getBookingType().equals("Secondary")){
		slotDao.updateSlotStatus("Primary Booked", clubSlot.getSlotId(),-(clubSlot.getPlayerCount()),"Y",clubSlot.getBookingId());
	    }
		else {
	    	slotDao.updateSlotStatus("Created", clubSlot.getSlotId(),-(clubSlot.getPlayerCount()),"Y",clubSlot.getBookingId());
	    }
		return "Successfully Deleted";
		
	}

	@Override
	public Object deleteUserBooking(ClubSlotBooking clubSlot) {
		Map<String, Object> outputMap = new HashMap<String, Object>();
				
		ClubSlot slotTime = slotDao.findBySlotId(clubSlot.getSlotId());
		
		Timestamp slotedTime = clubSlot.getSlotStartTimeStamp();
		//Timestamp slotedTime = slotTime.getSlotStartTimestamp();

		
		 // Convert Timestamp to LocalDateTime
	    LocalDateTime createdDateTime = slotedTime.toLocalDateTime();
	    	    
	    // Get the current date and time
	    LocalDateTime now = LocalDateTime.now();
	    System.out.println("---------"+now);
	    // Get the threshold time (24 hours before the booking time)
	    LocalDateTime thresholdTime = createdDateTime.minusHours(24);
	    // Check if the current time is before the threshold time
	    if (now.isBefore(thresholdTime)) {
	        // Update approval status to "Booking Cancelled"
	        slotBookingDao.updateApprovalStatus("Cancelled By User", clubSlot.getBookingId());
	        userbookDao.updateApprovalStatus("Cancelled By User", clubSlot.getBookingId());

	        // Check the booking type and update slot status accordingly
//	        if (bookingType.equals("Primary")) {
	            slotDao.updateSlotStatus("Created", clubSlot.getSlotId(), -(clubSlot.getPlayerCount()), "Y", clubSlot.getBookingId());
	            outputMap.put("Status", "Successfully Deleted");
	        } else {
	        	outputMap.put("Status", "Failed");
				outputMap.put("Message", "Booking cannot be deleted as it is within 24 hours of the booking time.");
	        }
	      //  return "Successfully Deleted";
	   // } 
	    //return "Successfully Deleted";
		return outputMap;
	}



	@Override
	public Object listapprovallist(ClubSlotBooking clubSlot) {
		// TODO Auto-generated method stub
		
		List<ClubSlotBooking> slotBookingList = slotBookingDao.findByPrimaryBookingIdAndBookingStatus(clubSlot.getUserId(),"Request for Approval");
		if(slotBookingList!=null ) {
			slotBookingList.forEach(slot->{
				ClubSlot slotDetail = new ClubSlot();
				slotDetail=slotDao.findBySlotId(slot.getSlotId());
				System.out.println(slotDetail);
				slot.setClubSlot(slotDetail);
			});
		}
		
		return slotBookingList;
	}




	@Override
	public List<UserBooking> findByBookingId(Integer bookingid) {
		// TODO Auto-generated method stub
		return userbookDao.findByBookingId(bookingid);
	}
	

	@Override
	public List<UserBooking> updateBooking(ClubSlotBooking booking) {
		// TODO Auto-generated method stub
		slotBookingDao.save(booking);
		List<UserBooking>  userList = new ArrayList<>();
		if(booking.getBookingId()!=null) {
			userbookDao.deleteByBookingId(booking.getBookingId());
		}
		booking.getUserBooking().forEach(user->{
			
				user.setBookingId(booking.getBookingId());
			
				userList.add(user);
				
	
			
		});
		return (List<UserBooking>) userbookDao.saveAll(userList);
	}




	@Override
	public Object listActiveBooking(Date slotDate) {
		// TODO Auto-generated method stub
		List<ClubSlotBooking> activeBookinglist  = new  ArrayList<>();
		activeBookinglist= slotBookingDao.findBySlotDateAndBookingStatusNot(slotDate, "Booking Cancelled");
		activeBookinglist.forEach(booking->{
			ClubSlot slotDetail = new ClubSlot();
			slotDetail=slotDao.findBySlotId(booking.getSlotId());
			System.out.println(slotDetail);
			booking.setClubSlot(slotDetail);
		});
		


List<ClubSlotBooking> res = activeBookinglist.stream()             
                    .sorted(Comparator.comparing(fo->fo.getClubSlot().getSlotStartTimeStamp()))
                    .collect(Collectors.toList());		
		return res;
		  
	}

	
	

	
}
