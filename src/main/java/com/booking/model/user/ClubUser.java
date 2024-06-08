package com.booking.model.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the ownnten_user database table.
 * 
 */
@Entity
@Table(name="CLUB_USER")
public class ClubUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;


	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Transient
	private String  otpNumber;
	public String getOtpNumber() {
		return otpNumber;
	}



	public void setOtpNumber(String otpNumber) {
		this.otpNumber = otpNumber;
	}



	public String getOtp() {
		return otp;
	}



	public void setOtp(String otp) {
		this.otp = otp;
	}


	@Column(name="DOB")
	private Timestamp dateofBirth;
	
	@Column(name="OTP")
	private String otp;
	

	
	
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



	@Column(name="FULL_NAME")
	private String fullName;
	

	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



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



	public Set<Roles> getRoles() {
		return roles;
	}



	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}



	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_image_url")
	private String userImageUrl;

	@Column(name="user_lname")
	private String userLname;
	

	@Column(name="user_fname")
	private String userFname;

	@Column(name="user_mobile")
	private String userMobile;

	@Column(name="user_password")
	private String userPassword;

	@Column(name="user_state")
	private String userState;
	
	@Column(name="status")
	private String status;
	

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	    @JoinTable(name = "USER_ROLES",
	            joinColumns = {
	            @JoinColumn(name = "USER_ID")
	            },
	            inverseJoinColumns = {
	            @JoinColumn(name = "ROLE_ID") })
	    private Set<Roles> roles;



	

}