package com.booking.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.dao.UserDao;
import com.booking.model.SlotBooked;
import com.booking.model.slot.UserBooking;
import com.booking.model.user.ClubUser;
import com.booking.model.user.MetaData;
import com.booking.model.user.Roles;
import com.booking.model.user.UserDto;
import com.booking.dao.UserBookingDao;
import com.booking.service.RoleService;
import com.booking.service.UserService;
import com.booking.uimodel.UIResponse;
import com.booking.util.EmailService;
import com.booking.util.SendEmailSmtp;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;
    
    @Autowired
	EmailService emailService;
    
    @Autowired	
	private SendEmailSmtp sendEmailSmtpService;
    
    @Autowired
    private UserBookingDao userBookingDao;
    
   @Autowired
   OtpServiceImpl otpServiceImpl;

	 @PersistenceContext
	 private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String userMObile) throws UsernameNotFoundException {
        ClubUser user = userDao.findByUserMobile(userMObile);
        System.out.println("In User UserServiceImpl");
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserMobile(), user.getUserPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(ClubUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

 
    @Override
    public ClubUser findOne(String userMobile) {
    //    ClubUser mobileNumber = userDao.findByUserMobile(userMobile);

//     
//        // Generate OTP
//        String token = emailService.generateOtp(6);
//
//        // Set OTP to the found user
//        mobileNumber.setOtp(token);
//
//        // Save the updated user
//        userDao.save(mobileNumber);
//
//        // Send OTP via WhatsApp
//        otpServiceImpl.sendWhatsappMessage(userMobile, mobileNumber.getOtp());
//        logger.info("OTP sent successfully to {}", userMobile);
//        logger.info("OTP sent successfully to {}", mobileNumber.getUserMobile());
        return userDao.findByUserMobile(userMobile);
    }

//    @Override
//    public ClubUser findOne(String userMobile) {
////    	ClubUser user = new ClubUser();
////    	
////    	System.out.println(user.getUserMobile());
////
//   	ClubUser mobileNumber = userDao.findByUserMobile(userMobile);
////    	
//    	System.out.println(mobileNumber.getUserMobile());
////		
////    	String	token = otpServiceImpl.generateOtp(6);
////		
////    	user.setOtp(token);
////    	
////    int data =	otpServiceImpl.sendWhatsappMessage(userMobile, token);
//    	
//    	
//    	
//    	return mobileNumber;
//    }
    
    @Override
    public ClubUser save(UserDto user) {
    	
    	
    
    	
    	user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		/*
		 * role.setRoleId(user.getRoleId()); List<Roles> roles = new ArrayList<Roles>();
		 * roles.add(role);
		 */ 
     
        return userDao.save(convertToEntity(user));
    	}
    
    //DAO Dao
    @Override
    public ResponseEntity<UIResponse> checkandsave(UserDto user) {
    	UIResponse uiResponse = new UIResponse();
		/*
		 * if( userDao.findByUserMobileOrUserEmail(user.getUserMobile(),
		 * user.getUserEmail()) !=null) { uiResponse.setStatus("FAILURE");
		 * Map<String,String> respone= new HashMap<String, String>();
		 * respone.put("response", "User Mobile or Email id Already Exists");
		 * uiResponse.setResponse(respone); return new ResponseEntity<>(uiResponse,
		 * HttpStatus.OK); }
		 */
    //	else {
    	
    
    	user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		/*
		 * role.setRoleId(user.getRoleId()); List<Roles> roles = new ArrayList<Roles>();
		 * roles.add(role);
		 */ 
         
        
        
        uiResponse.setStatus("Success");
		uiResponse.setResponse(userDao.save(convertToEntity(user)));
		return new ResponseEntity<>(uiResponse, HttpStatus.OK);
    	}
    	//}



	@SuppressWarnings("unchecked")
	@Override
	public List<MetaData> findAll() {
		// TODO Auto-generated method stub
		 List<MetaData> clubuserList= userDao.findAllUserList();
		 
		
		 
		 return clubuserList;
	};
	
	
	
	private ClubUser convertToEntity(UserDto user) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		ClubUser newuser = modelMapper.map(user, ClubUser.class);
		 Roles role = new Roles();
		 role.setRoleId(user.getRoleId());
	        Set<Roles> roles = new HashSet<Roles>();
	        roles.add(role); 
	        newuser.setRoles(roles);
	    return newuser;
	}

	@Override
	public ClubUser saveorupdate(UserDto user) {
		ClubUser newuser =new ClubUser();
		
		newuser = userDao.findByUserMobile(user.getUserMobile());
	    if(newuser!=null && newuser.getUserId()!=null) {
	    	return newuser;
	    }else {
	    	user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
	    	return userDao.save(convertToEntity(user));
	    }
		
	}

	@Override
	public Object listUser(UserDto searchCriteria) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClubUser> cq = cb.createQuery(ClubUser.class);
        Root<ClubUser> rootuser = cq.from(ClubUser.class);
        List<Predicate> predicates = new ArrayList<>();

      
        
        
        
        cq.where(predicates.toArray(new Predicate[0])); 
        cq.orderBy(cb.desc(rootuser.get("createdDate")));  
        return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public Object updatePassword(UserDto user) {
		user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		
		return userDao.updatePassword(user.getUserMobile(),user.getUserPassword());
	}
	
	

	@Override
	public Object deleteuser(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.deleteByUserId(userId);
	}

	@Override
	public ResponseEntity<UIResponse> listByUserAndDate(UserBooking user) {
		SlotBooked SlotBooked;
		
		UIResponse uiResponse = new UIResponse();
		
		// TODO Auto-generated method stub
		 SlotBooked = userBookingDao.findByUserIdSlotDate(user.getUserId(),user.getSlotDate());
		 if(SlotBooked.getSlotBooked().equals(0)) {
			 uiResponse.setStatus("SUCCESS");
			 uiResponse.setResponse(SlotBooked.getSlotBooked());
		 }else {
			 uiResponse.setStatus("FAILURE");
			 uiResponse.setResponse(SlotBooked.getSlotBooked());
		 }
		 
		 return new ResponseEntity<>(uiResponse, HttpStatus.OK); 
	}

	@Override
	public ClubUser updateProfile(UserDto user) {
		// TODO Auto-generated method stub
		ClubUser dbuser = userDao.findByUserId(user.getUserId());
		dbuser.setUserFname(user.getUserFname());
		dbuser.setUserFname(user.getUserFname());
		dbuser.setUserLname(user.getUserLname());
		dbuser.setUserMobile(user.getUserMobile());
		dbuser.setUserEmail(user.getUserEmail());;
		 return userDao.save(dbuser);
	}

	@Override
	public ClubUser findByUserEmail(ClubUser userEmail) {
		// TODO Auto-generated method stub
		return userDao.findByUserEmail(userEmail.getUserEmail());
	}

	@Override
	public void updateResetPasswordToken(ClubUser user, String email) {

		String token=emailService.generateOtp(6);
           user.setOtp(token);
           sendEmailSmtpService.sendTokenEmail(email, token, user);
           logger.info("email sent successfully to {}",email);
           
           userDao.save(user);
	}

	@Override
	public String validateOtp(String otp, UserDto user) {
	    String success = "OTP has been validated and password has been updated";
	    String invalid = "Invalid OTP";
	    ClubUser clubUser = userDao.findByUserEmail(user.getUserEmail());
        if (clubUser == null) {
            return invalid;
        }

        // Validate OTP
        if (clubUser.getOtp().equals(otp)) {
            // Encode the new password
            String encodedPassword = bcryptEncoder.encode(user.getUserPassword());

            // Update the user's password
            userDao.updatePassword(user.getUserEmail(), encodedPassword);

            return success;
        }

        return invalid;
    }
	}

//	public boolean otpToWhatsapp(ClubUser user) {
//	    try {
//	        String token = emailService.generateOtp(6);  // Generate a 6-digit OTP
//	        user.setOtp(token);  // Set the OTP in the user object
//	        otpServiceImpl.sendWhatsappMessage(user, token);  // Send the OTP via WhatsApp
//	        logger.info("OTP sent successfully to {}", user.getUserMobile());
//	        return true;  // Return true to indicate success
//	    } catch (Exception e) {
//	        logger.error("Failed to send OTP to {}: {}", user.getUserMobile(), e.getMessage());
//	        return false;  // Return false to indicate failure
//	    }
//	}
//	
	
	


