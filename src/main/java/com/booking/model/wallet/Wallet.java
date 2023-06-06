package com.booking.model.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class Wallet {
	public Wallet() {
		super();
	}
	
	@Column(name = "wallet_id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer  walletId;	
    Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Date  updatedTimeStamp;
	private double walletAmount;
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	public double getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
}
