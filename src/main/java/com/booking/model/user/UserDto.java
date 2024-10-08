package com.booking.model.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    
	

	@Column(name="user_id")
	private Integer userId;


	
	@Column(name="role_id")
	private Integer roleId;
	public Integer getRoleId() {
		return roleId;
	}



	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	@Column(name="FULL_NAME")
	private String fullName;



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;


    
	

	@Column(name="user_address")
	private String userAddress;

	@Column(name="user_city")
	private String userCity;

	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
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



	public String getUserAddress() {
		return userAddress;
	}



	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}



	public String getUserCity() {
		return userCity;
	}



	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserImageUrl() {
		return userImageUrl;
	}



	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}



	public String getUserLname() {
		return userLname;
	}



	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}



	public String getUserFname() {
		return userFname;
	}



	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}



	public String getUserMobile() {
		return userMobile;
	}



	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getUserState() {
		return userState;
	}



	public void setUserState(String userState) {
		this.userState = userState;
	}






	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_image_url")
	private String userImageUrl;

	@Column(name="user_lname")
	private String userLname;
	@Column(name="DOB")
	private Timestamp dateofBirth;
	
	public Timestamp getDateofBirth() {
		return dateofBirth;
	}



	public void setDateofBirth(Timestamp dateofBirth) {
		this.dateofBirth = dateofBirth;
	}



	public Timestamp getValidity() {
		return validity;
	}



	public void setValidity(Timestamp validity) {
		this.validity = validity;
	}



	public String getMembershipType() {
		return membershipType;
	}



	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}



	@Column(name="validity")
	private Timestamp validity;

	@Column(name="MEMBERSHIP_TYPE")
	private String membershipType;
	
	@Column(name="MEMBERSHIP_NUMBER")
	private String membershipNumber;

	public String getMembershipNumber() {
		return membershipNumber;
	}



	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}






	@Column(name="user_lname")
	private String userFname;

	@Column(name="user_mobile")
	private String userMobile;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_state")
	private String userState;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Transient
	private String  otpNumber;
	public String getOtpNumber() {
		return otpNumber;
	}



	public void setOtpNumber(String otpNumber) {
		this.otpNumber = otpNumber;
	}
    
}