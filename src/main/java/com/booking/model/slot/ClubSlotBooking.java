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

import com.booking.model.user.ClubUser;


/**
 * The persistent class for the ownnten_user database table.
 * 
 */
@Entity
@Table(name="CLUB_SLOT_BOOKING")
@NamedQuery(name="ClubSlotBooking.findAll", query="SELECT o FROM ClubSlotBooking o")
public class ClubSlotBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Integer bookingId;
	
	
	public ClubSlot getClubSlot() {
		return clubSlot;
	}

	public void setClubSlot(ClubSlot clubSlot) {
		this.clubSlot = clubSlot;
	}

	@Column(name="slot_id")
	private Integer slotId;
	
	
	@Transient
	private ClubSlot clubSlot;
	
	
	public ClubUser getClubUser() {
		return clubUser;
	}

	public void setClubUser(ClubUser clubUser) {
		this.clubUser = clubUser;
	}

	@Transient
	private ClubUser clubUser;


	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="PLAYER")
	private String player;

	
	@Column(name="SECONDARY_BOOKING")
	private String secondaryBooking;

	public String getPlayer() {
		return player;
	}

	public String getSecondaryBooking() {
		return secondaryBooking;
	}

	public void setSecondaryBooking(String secondaryBooking) {
		this.secondaryBooking = secondaryBooking;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

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

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	

	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="booking_type")
	private String bookingType;
	
	@Column(name="user_id")
	private Integer userId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	



	



	
	




	

}