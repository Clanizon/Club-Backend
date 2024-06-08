package com.booking.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.SplittableRandom;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.UserDao;
import com.booking.model.user.ClubUser;
@Service
public class OtpServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    private UserDao userDao;
    

	
	public  int sendWhatsappMessage(String userMobile,String otp){

        int reutrnVal=0;
        //GupShup Whatsapp
            	
        	reutrnVal = sendGupShupWhatsAppMessageOPTIN( userMobile, otp);
            reutrnVal = sendGupShupWhatsAppMessage( userMobile, otp);

        return reutrnVal;

    }
	
	
	
	private static int sendGupShupWhatsAppMessageOPTIN(String userMobile,String otp){

        int result=0;
        try {
        	 ClassLoader classLoader = OtpServiceImpl.class.getClassLoader();
             InputStream input = classLoader.getResourceAsStream("application.properties");
             Properties prop = new Properties();
             prop.load(input);
             Properties property = new Properties();
//             if ("dev".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-dev.properties"));
//             }
//             else if ("stage".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-stage.properties"));
//             } else if ("prod".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-prod.properties"));
//             }
             String whatsAppGateway = property.getProperty("whatsapp.gateway");
            String userid = "2000209598";
//            String password = "Curebay@123";
            String method = "OPT_IN";
            String auth_scheme = "plain";
            String v = "1.1";
            String channel = "WHATSAPP";

            String requestUrl = "https://media.smsgupshup.com/GatewayAPI/rest?" +
            "userid=" + URLEncoder.encode(userid, "UTF-8") +
            "&password=" + URLEncoder.encode(whatsAppGateway, "UTF-8") +
            "&phone_number=" + URLEncoder.encode(userMobile, "UTF-8") +
            "&method=" + URLEncoder.encode(method, "UTF-8") +
            "&auth_scheme=" + URLEncoder.encode(auth_scheme, "UTF-8") +
            "&v=" + URLEncoder.encode(v, "UTF-8") +
            "&channel=" + URLEncoder.encode(channel, "UTF-8");
   
           logger.info("GUPSHUP URL OPTIN :"+requestUrl);

            URL url = new URL(requestUrl);
            HttpsURLConnection uc = (HttpsURLConnection)url.openConnection();
          uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            if(uc.getResponseCode()==200) {

                result=1;
            }
            uc.connect();

        } catch(Exception ex) {
        	// log.info("SendWhatsapp.java -->> Exception found in sendGupShupWhatsAppMessageOPTIN method :::",ex.getMessage());
        }
        return result;
    }
	
	
	
	
    private static int sendGupShupWhatsAppMessage(String userMobile,String otp){
        int result=0;
       
        try {

        	
        	 ClassLoader classLoader = OtpServiceImpl.class.getClassLoader();
             InputStream input = classLoader.getResourceAsStream("application.properties");
             Properties prop = new Properties();
             prop.load(input);
             Properties property = new Properties();
//             if ("dev".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-dev.properties"));
//             } 
//             else if ("stage".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-stage.properties"));
//             } else if ("prod".equalsIgnoreCase(prop.getProperty("spring.profiles.active"))) {
//                 property.load(classLoader.getResourceAsStream("application-prod.properties"));
//             }
            // String whatsAppGateway = property.getProperty("whatsapp.gateway");
            String userid = "2000209598";
           String password = "Curebay@123";
            String method = "SendMessage";
            String auth_scheme = "plain";
            String v = "1.1";
            String channel = "WHATSAPP";
            String msg = "Your+Mobile+Number+Verification+Code+is+"+otp+".+This+OTP+Code+is+valid+for+5+Minutes.%0ARegards%2C+Bhubaneswar+Golf+Club.";
            String msg_type = "TEXT";
            String format = "json";
            String send_to = userMobile;
            String isTemplate = "true";

            String requestUrl = "https://media.smsgupshup.com/GatewayAPI/rest?" +

            "method=" + URLEncoder.encode(method, "UTF-8") +
            "&userid=" + URLEncoder.encode(userid, "UTF-8") +
            "&password=" + URLEncoder.encode(password, "UTF-8") +
            "&msg="+URLEncoder.encode(msg, "UTF-8") +
            "&msg_type=" + URLEncoder.encode(msg_type, "UTF-8") +
            "&format=" + URLEncoder.encode(format, "UTF-8") +
            "&v=" + URLEncoder.encode(v, "UTF-8") +
            "&auth_scheme=" + URLEncoder.encode(auth_scheme, "UTF-8") +
            "&send_to=" + URLEncoder.encode(send_to, "UTF-8") ;

           logger.info("GUPSHUP URL FOR SEND MESSAGE :"+requestUrl);

            URL url = new URL(requestUrl);
            HttpsURLConnection uc = (HttpsURLConnection)url.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            if(uc.getResponseCode()==200) {

                result=1;
            }
          uc.connect();
        } catch(Exception ex) {

        	//log.info("SendWhatsapp.java -->> Exception found in sendGupShupWhatsAppMessage method :::",ex.getMessage());
        }
        return result;
    }

//    
//    public   String generateOtp(int otpLength) {
//
//		SplittableRandom splittableRandom = new SplittableRandom();
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < otpLength; i++) {
//
//			sb.append(splittableRandom.nextInt(0, 10));
//		}
//		return sb.toString();
//
//	}
    
}
