package com.booking.util;

import org.jboss.logging.Logger;

import java.util.Properties;
import java.util.SplittableRandom;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	 private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

		@Value( "${spring.mail.host}" )
		private String host;
		
		
		@Value( "${spring.mail.properties.mail.smtp.auth}" )
		private String auth;
		
		
		@Value( "${spring.mail.port}" )
		private String port;
		
		@Value( "${spring.mail.properties.mail.smtp.starttls.enable}" )
		private String starttls;
		
		@Value( "${spring.mail.dvp.from}" )
		private String from;
		
		@Value( "${spring.mail.fmea.password}" )
		private String password;

		public void sendTestEmail(String to, String subject, String body,String userEmail) throws MessagingException {


			String from = "venkadeshj@clanizon.com"; // sender email address
			//String password = "nzogjorintabruki"; // sender email password
			String password = "mvwfzagsxycvgthc"; // sender email password

			// SMTP server properties
//			 LOGGER.log(Level.FINE, "  host = {0}", host);
//			 LOGGER.log(Level.FINE, "  auth = {0}", auth);
//			 LOGGER.log(Level.FINE, "  port = {0}", port);
//			 LOGGER.log(Level.FINE, "  starttls = {0}", starttls);
//			 LOGGER.log(Level.FINE, "  To Mail = {0}", to);
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth",auth);
			props.put("mail.smtp.port",port);
			props.put("mail.smtp.starttls.enable", starttls);

			// create session

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			try {
				// create message

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(userEmail));
				message.setSubject(subject);
				//message.setText(body);
				message.setContent(body,"text/html");

				// send message
				Transport.send(message);
			} catch (MessagingException e) {

				throw e;
			}
		}


		public void sendEmail(String to, String subject, String body,String userEmail) throws MessagingException {


		//	String from = "esgemailservice@dovercorp.com";// sender email address
		// password = "BM@3we4rt5";// sender email password

			// SMTP server properties
			Properties props = new Properties();
//			props.put("mail.smtp.host", "smtp.dovercorporation.com");
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.port", "25");

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth",auth);
			props.put("mail.smtp.port",port);
			props.put("mail.smtp.starttls.enable", starttls);
			// create session

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			try {
				// create message

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(userEmail));
				message.setSubject(subject);
				//message.setText(body);
				message.setContent(body,"text/html");


				// send message
				Transport.send(message);
			} catch (MessagingException e) {

				throw e;
			}
		}
		public  String generateOtp(int otpLength) {

			SplittableRandom splittableRandom = new SplittableRandom();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < otpLength; i++) {

				sb.append(splittableRandom.nextInt(0, 10));
			}
			return sb.toString();

		}


}
