package com.booking.uimodel;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the property_invoice database table.
 * 
 */

public class PropertyInvoice implements Serializable {
	private static final long serialVersionUID = 1L;
    

	private int propertyInvoiceId;
	
	public int getPropertyInvoiceId() {
		return propertyInvoiceId;
	}

	public void setPropertyInvoiceId(int propertyInvoiceId) {
		this.propertyInvoiceId = propertyInvoiceId;
	}

	private String  invoiceStatus;

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	@Column(name="created_by")
	private Integer createdBy;
	
	public String getRazorpayAccountId() {
		return razorpayAccountId;
	}

	public void setRazorpayAccountId(String razorpayAccountId) {
		this.razorpayAccountId = razorpayAccountId;
	}

	private String razorpayAccountId;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="due_date")
	private String dueDate;
	
	@Column(name="invoice_type")
	private String invoiceType;
	
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}
	
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	@Column(name="invoice_description")
	private String invoiceDescription;

	@Column(name="monthy_due")
	private BigDecimal monthyDue;

	@Column(name="owner_id")
	private Integer ownerId;

	@Column(name="property_id")
	private Integer propertyId;

	@Column(name="tenant_id")
	private Integer tenantId;

	@Column(name="total_due")
	private BigDecimal totalDue;
	
	@Column(name="user_account_id")
	private Integer userAccountId;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public PropertyInvoice() {
	}



	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	

	public BigDecimal getMonthyDue() {
		return this.monthyDue;
	}

	public void setMonthyDue(BigDecimal monthyDue) {
		this.monthyDue = monthyDue;
	}

	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public BigDecimal getTotalDue() {
		return this.totalDue;
	}

	public void setTotalDue(BigDecimal totalDue) {
		this.totalDue = totalDue;
	}

	

}