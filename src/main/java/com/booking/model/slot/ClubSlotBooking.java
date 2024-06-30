package com.booking.model.slot;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

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
	
	
	@Column(name="booking_Status")
	private String bookingStatus;
	
	
	public Integer getPrimaryBookingId() {
		return primaryBookingId;
	}

	public void setPrimaryBookingId(Integer primaryBookingId) {
		this.primaryBookingId = primaryBookingId;
	}

	@Column(name="PLAYER_COUNT")
	private Integer playerCount;
	
	
	@Column(name="PRIMARY_BOOKING_ID")
	private Integer primaryBookingId;
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(Integer playerCount) {
		this.playerCount = playerCount;
	}

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
	
	
	@Transient
	private Timestamp slotStartTimeStamp;
	


	public Timestamp getSlotStartTimeStamp() {
		return slotStartTimeStamp;
	}

	public void setSlotStartTimeStamp(Timestamp slotStartTimeStamp) {
		this.slotStartTimeStamp = slotStartTimeStamp;
	}

	public ClubUser getClubUser() {
		return clubUser;
	}

	public void setClubUser(ClubUser clubUser) {
		this.clubUser = clubUser;
	}

	
	

	public Date getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}

	@Column(name="slot_date")
	private Date slotDate;
	
	


	@Transient
	private ClubUser clubUser;
	
	
	public List<UserBooking> getUserBooking() {
		return userBooking;
	}

	public void setUserBooking(List<UserBooking> userBooking) {
		this.userBooking = userBooking;
	}

	@Transient
	private List<UserBooking> userBooking;


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