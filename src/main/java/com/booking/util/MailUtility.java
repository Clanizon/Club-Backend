package com.booking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class MailUtility  {

		@Autowired
	    private org.springframework.mail.javamail.JavaMailSender javaMailSender;
	    
   
    public String sendEmail(String subject,String message,String ToAddress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        
          msg.setTo(ToAddress);
        System.out.println(message);
        System.out.println(ToAddress);
        msg.setSubject(subject);
        msg.setText(message);
        javaMailSender.send(msg);
        return "success";
    }
/*
    public void sendEmailWithAttachment(String userId,String ) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(userId);

        helper.setSubject("welcome to CRM Application");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1></h1> Please use below OTP to login "+OTP, true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("bg.jpg", new ClassPathResource("bg.jpg"));

        javaMailSender.send(msg);

    }
    
    */
}