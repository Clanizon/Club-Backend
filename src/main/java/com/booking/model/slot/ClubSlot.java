package com.booking.model.slot;

import java.io.Serializable;
import java.sql.Timestamp;

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
@NamedQuery(name="ClubSlot.findAll", query="SELECT o FROM ClubSlot o")
public class ClubSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slot_id")
	private Integer slotId;


	
    


	public Integer getSlotId() {
		return slotId;
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
	
	
   



	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}



	public Timestamp getSlotDate() {
		return slotDate;
	}



	public void setSlotDate(Timestamp slotDate) {
		this.slotDate = slotDate;
	}



	public Timestamp getSlotStartTimeStamp() {
		return slotStartTimeStamp;
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
	private Timestamp slotDate;

	
	@Column(name="SlOT_START_TIMESTMP")
	private Timestamp slotStartTimeStamp;
	
	
	@Column(name="SlOT_END_TIMESTMP")
	private Timestamp slotEndTimeStamp;
	
	

	@Column(name="SLOT_STATUS")
	private String slotStatus;
	
	
	@Column(name="SLOT_AVAILABLE")
	private String slotAvailable;






	public String getSlotAvailable() {
		return slotAvailable;
	}



	public void setSlotAvailable(String slotAvailable) {
		this.slotAvailable = slotAvailable;
	}





}