package com.booking.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.config.TokenProvider;
import com.booking.model.user.ClubUser;
import com.booking.model.user.LoginUser;
import com.booking.model.user.UserDto;
import com.booking.service.ApiHelper;
import com.booking.service.UserService;
import com.booking.uimodel.OTPModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;
    
  

    

    
   private HashMap<String, String> otpCache = new HashMap<String, String>();
   
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Object generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserMobile(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        ClubUser user= userService.findOne( loginUser.getUserMobile());
       // user.setUserPassword(token);
        return user;
        //return ResponseEntity.ok(new AuthToken(token));
    }
    
    @RequestMapping(value = "/validateOTP", method = RequestMethod.POST)
    public Object otpLogin(@RequestBody OTPModel user) throws AuthenticationException {

    	if(otpCache.get(user.getMobile())!=null && otpCache.get(user.getMobile()).equals(user.getOtp()))
    	{		
	        ClubUser ownntenUser= userService.findOne( user.getMobile());
	        return ownntenUser;
    	}  else {  
        return "OTP validation failed";
    	}
    }
    
    
    @RequestMapping(value = "/listuser", method = RequestMethod.POST)
    public Object listuser(@RequestBody OTPModel user) throws AuthenticationException {
    	return userService.findAll();
    	
    }
    @RequestMapping(value = "/sendOTP", method = RequestMethod.POST)
    public Object otpLogin(@RequestBody LoginUser loginUser) throws AuthenticationException {

    	ClubUser user;
    	if (loginUser.getUserMobile()!=null && !"".equals(loginUser.getUserMobile().trim()))
    	{ 
    		 user= userService.findOne( loginUser.getUserMobile());
    		 if(user!=null) {
    	        	String message ="Your Mobile Number Verification Code is "+generateOTP(user.getUserMobile())+". This OTP Code is valid for 5 Minutes One Assist Monetary Consultant Regards NC";
    	            ApiHelper.getInstance().SendMessage((respone)->{
    	        	  
    	            },user.getUserMobile(),message
    	            );
    	            return "OTP sent to user";
    	        }
    		 else
    			 return "User not exist";

    			 
        
    	}
    	 else
			 return "User not exist";
        
        
    }
    @RequestMapping(value = "/isUserExist", method = RequestMethod.POST)
    public Object isUserExist(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	ClubUser user;
    	if (loginUser.getUserMobile()!=null && !"".equals(loginUser.getUserMobile().trim()))
    	{ 
    		 user= userService.findOne( loginUser.getUserMobile());
        if(user!=null) {
        	return user;
        }
        
    	}
    	else if (loginUser.getUsername()!=null && !"".equals(loginUser.getUsername().trim()))
    	{
    		 user= userService.findOne( loginUser.getUsername());
        if(user!=null) {
        	return user;
        }
    	}
        return "{\"message\" : \"User not exist\" }";
    }
    @RequestMapping(value="/updatepassword", method = RequestMethod.POST)
    public Object updatePassword(@RequestBody UserDto user){
    	try {
        return userService.updatePassword(user);
        }
    	catch(Exception e) {
    		return e;
    	}
    }
    

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Object saveUser(@RequestBody UserDto user){
    	try {
        return userService.checkandsave(user);
        }
    	catch(Exception e) {
    		return e;
    	}
    }

    
    
    
    @RequestMapping(value="/otpsend", method = RequestMethod.POST)
    public String sendOTP(@RequestBody OTPModel user){
    	  String message ="Your Mobile Number Verification Code is "+user.getOtp()+". This OTP Code is valid for 5 Minutes One Assist Monetary Consultant Regards NC";
          ApiHelper.getInstance().SendMessage((respone)->{
        	  System.out.println(respone);
        	  
          },user.getMobile(),message
          );
		return message;
    }
    
    



    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }
    
    
    
    
    
    

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }
    
//    @RequestMapping(value="/contactus", method = RequestMethod.POST)
//	public Object contactUs(@RequestBody ContactUsModel contactUsModel) {
//    	try {
//    		String msgTxt= "<h1>Full Name: "+contactUsModel.getFullName()+" Phone Number: "+contactUsModel.getPhoneNumber()+" Message: "+contactUsModel.getMessage()+"</h1>";
//    		emailService.sendEmail("Contact Us",msgTxt,"info@ownnten.com");
//	    }
//		catch(Exception e) {
//			return e;
//		}
//	    return contactUsModel;
//	}

    public String generateOTP(String key) {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		String otp =String.format("%06d", number);
		otpCache.put(key, otp);
		return otp;
	}
}
