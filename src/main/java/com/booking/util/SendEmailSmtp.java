package com.booking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.booking.model.user.ClubUser;
import com.booking.model.user.UserDto;

@Service
public class SendEmailSmtp {
	@Autowired
	EmailService emailService;
	

	@Value( "${spring.env.name}" )
	private String env;
	
	public  void sendTokenEmail(String to,String otp,ClubUser user) {
	
		
		String bodyMessage=createBodyMessageForgotPassword(user,otp);
		
		try {
		
		if(env.equals("prod"))
		{
			
			emailService.sendEmail(to,"Forgot Password",bodyMessage,"");	
		}
		else {
			emailService.sendTestEmail(to,"Forgot Password",bodyMessage,"");
		}
		
		System.out.println("Email sent successfully.");
		}catch (Exception e) {
			System.out.println("Email sent Exception :"+ e.getMessage());
		}	
	}
	public  void sendEmail(String to,String subject,String body,String cc) {
		try {
		
		if(env.equals("prod"))
		{
			
			emailService.sendEmail(to,subject,body,cc);	
		}
		else {
			emailService.sendTestEmail(to, subject,body,cc);
		}
		
		System.out.println("Email sent successfully.");
		}catch (Exception e) {
			System.out.println("Email sent Exception :"+ e.getMessage());
			e.printStackTrace();
		}	
	}
	
		public String createBodyMessageForgotPassword(ClubUser user,String otp) {
			String newline ="<br>"; 
			String bodyMessage="You (";
			StringBuffer emailMessage = new StringBuffer(bodyMessage);
			emailMessage.append(user.getUserFname());
			emailMessage.append(" "+user.getUserLname());
			emailMessage.append(" - "+user.getUserEmail());
			emailMessage.append(") have raised a request to reset your GOLF application password. Please find the enclosed token required to initiate the password reset process.");
			emailMessage.append(newline);
			emailMessage.append(newline);
			emailMessage.append("Token : "+otp);
			return emailMessage.toString();
			
		}
}
