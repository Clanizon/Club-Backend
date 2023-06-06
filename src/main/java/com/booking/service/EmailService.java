package com.booking.service;



public interface EmailService {
	
	void sendEmail(String subject,String message,String ToAddress)throws InterruptedException;

	
}
