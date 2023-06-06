package com.booking.model.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class WalletAudit {
	public WalletAudit() {
		super();
	}

	@Column(name = "wallet_audit_id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer walletAuditId;
	String action;
	Integer walletId;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	Integer userId;
	String walletSource;

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	Double auditAmount;
	String orderId;
	private Date updatedTimeStamp;

	public Integer getWalletAuditId() {
		return walletAuditId;
	}

	public void setWalletAuditId(Integer walletAuditId) {
		this.walletAuditId = walletAuditId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Double getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public String getWalletSource() {
		return walletSource;
	}

	public void setWalletSource(String walletSource) {
		this.walletSource = walletSource;
	}
}
