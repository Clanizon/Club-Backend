package com.booking.model.slot;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the ownnten_user database table.
 * 
 */
@Entity
@Table(name="user_booking")
public class UserBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_booking_id")
	private Integer userBookingId;
	
	@Transient
	private ClubSlot clubSlot;
	
	@Column(name="updated_date")
	private Timestamp updatedDate;
	
	@Column(name="updated_by")
	private String updatedBy;

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}






	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}






	public String getUpdatedBy() {
		return updatedBy;
	}






	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}






	@Column(name="DATE_MODIFIED")
	private Timestamp dateModified;
	
	


	public ClubSlot getClubSlot() {
		return clubSlot;
	}

	
	
	


	public String getSlotDate() {
		return slotDate;
	}


	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}






	@Transient
	private String slotDate;

	public void setClubSlot(ClubSlot clubSlot) {
		this.clubSlot = clubSlot;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Column(name="APPROVAL_STATUS")
	private String approvalStatus;
	
	
	@Column(name="user_name")
	private String userName;
	
	
	public String getPlayersName() {
		return playersName;
	}


	public void setPlayersName(String playersName) {
		this.playersName = playersName;
	}


	public String getBookingName() {
		return bookingName;
	}


	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}


	@Column(name="players_name")
	private String playersName;
	
	
	@Column(name="booking_name")
	private String bookingName;

	
	public Integer getUserBookingId() {
		return userBookingId;
	}


	public void setUserBookingId(Integer userBookingId) {
		this.userBookingId = userBookingId;
	}


	public Timestamp getDateModified() {
		return dateModified;
	}


	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}


	public String getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	public String getBookingType() {
		return bookingType;
	}


	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Integer getSlotId() {
		return slotId;
	}


	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name="booking_type")
	private String bookingType;
	
	
	
	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name="slot_id")
	private Integer slotId;
	
	
	@Column(name="booking_id")
	private Integer bookingId;
	

	@Column(name="user_id")
	private Integer userId;




}