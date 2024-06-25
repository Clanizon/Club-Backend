package com.booking.model.slot;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the ownnten_user database table.
 * 
 */
@Entity
@Table(name="CLUB_SLOT")

public class ClubSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slot_id")
	private Integer slotId;


	
	@Transient
	private List<Integer> slotDays;
	


	public List<Integer> getSlotDays() {
		return slotDays;
	}



	public void setSlotDays(List<Integer> slotDays) {
		this.slotDays = slotDays;
	}

	@Column(name="primary_booking_id")
	private Integer primaryBookingId;
    


	public Integer getSlotId() {
		return slotId;
	}



	public Integer getPrimaryBookingId() {
		return primaryBookingId;
	}



	public void setPrimaryBookingId(Integer primaryBookingId) {
		this.primaryBookingId = primaryBookingId;
	}



	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	@Column(name="PLAYER_COUNT")
	private Integer playerCount;
	
	public Integer getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(Integer playerCount) {
		this.playerCount = playerCount;
	}
   



	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}






	public Timestamp getSlotStartTimeStamp() {
		return slotStartTimeStamp;
	}


	
	public String getSlotStarted() {
		return slotStarted;
	}



	public void setSlotStarted(String slotStarted) {
		this.slotStarted = slotStarted;
	}

	@Transient
	String slotStarted;

	public Date getSlotDate() {
		return slotDate;
	}



	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}



	public void setSlotStartTimeStamp(Timestamp slotStartTimeStamp) {
		this.slotStartTimeStamp = slotStartTimeStamp;
	}



	public Timestamp getSlotEndTimeStamp() {
		return slotEndTimeStamp;
	}



	public void setSlotEndTimeStamp(Timestamp slotEndTimeStamp) {
		this.slotEndTimeStamp = slotEndTimeStamp;
	}

	@Transient
	private String start;
	

	@Transient
	private String end;
	

	public Integer getSlotDuration() {
		return slotDuration;
	}



	public void setSlotDuration(Integer slotDuration) {
		this.slotDuration = slotDuration;
	}

	@Transient
	private Integer slotDuration;
	
	
	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	public String getSlotStatus() {
		return slotStatus;
	}



	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}



	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="CLUB_NAME")
	private String clubName;
	
	
	@Column(name="SECONDARY_BOOKING")
	private String secondaryBooking;

	public String getSecondaryBooking() {
		return secondaryBooking;
	}



	public void setSecondaryBooking(String secondaryBooking) {
		this.secondaryBooking = secondaryBooking;
	}



	public String getClubName() {
		return clubName;
	}



	public void setClubName(String clubName) {
		this.clubName = clubName;
	}



	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="slot_date")
	private Date slotDate;

	
	@Column(name="SlOT_START_TIMESTMP")
	private Timestamp slotStartTimeStamp;
	
	
	@Column(name="SlOT_END_TIMESTMP")
	private Timestamp slotEndTimeStamp;
	
	

	@Column(name="SLOT_STATUS")
	private String slotStatus;
	
	@Column(name="TEE_TIME")
	private String teeTime;
	
    public String getTeeTime() {
		return teeTime;
	}



	public void setTeeTime(String teeTime) {
		this.teeTime = teeTime;
	}

	@Transient	
	private String bookingMessage;
	
	
	public String getBookingMessage() {
		return bookingMessage;
	}



	public void setBookingMessage(String bookingMessage) {
		this.bookingMessage = bookingMessage;
	}

	@Column(name="SLOT_AVAILABLE")
	private String slotAvailable;






	public String getSlotAvailable() {
		return slotAvailable;
	}



	public void setSlotAvailable(String slotAvailable) {
		this.slotAvailable = slotAvailable;
	}





}