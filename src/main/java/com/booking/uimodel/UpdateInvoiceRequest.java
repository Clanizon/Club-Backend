package com.booking.uimodel;

import java.sql.Timestamp;

public class UpdateInvoiceRequest {
	
	Integer invoiceMonth;
	
	public Integer getInvoiceMonth() {
		return invoiceMonth;
	}

	public void setInvoiceMonth(Integer invoiceMonth) {
		this.invoiceMonth = invoiceMonth;
	}

	public Integer getInvoiceYear() {
		return invoiceYear;
	}

	public void setInvoiceYear(Integer invoiceYear) {
		this.invoiceYear = invoiceYear;
	}

	Integer invoiceYear;
	
	Integer invoiceId;
	
	String Status;
	
	
	private Timestamp modifiedDate;
	

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	

}
