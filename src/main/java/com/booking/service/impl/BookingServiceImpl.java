package com.booking.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.ClubSlotBookingDao;
import com.booking.dao.ClubSlotDao;
import com.booking.dao.UserDao;
import com.booking.model.slot.ClubSlot;
import com.booking.model.slot.ClubSlotBooking;
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

 

   

		@Override
		public Object save(ClubSlotBooking clubSlotBooking) {
			// TODO Auto-generated method stub
			ClubSlotBooking newbooking = new ClubSlotBooking();
			UIResponse uiResponse = new UIResponse();
			ClubSlot clubSlot = slotDao.findBySlotId(clubSlotBooking.getSlotId());
			if (clubSlot != null)
			{
								if(clubSlot.getSlotStatus().equals("Created")) {
									    if(clubSlotBooking.getBookingType().equals("Primary"))
									    {
													newbooking = slotBookingDao.save(clubSlotBooking);
													slotDao.updateSlotStatus("Primary Booked", clubSlotBooking.getSlotId());
													
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
												slotDao.updateSlotStatus("Secondary Booked", clubSlotBooking.getSlotId());
												
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
			return slotBookingList;
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

	
	

	
}
