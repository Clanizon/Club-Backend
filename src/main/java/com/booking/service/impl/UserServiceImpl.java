package com.booking.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
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
import com.booking.model.user.ClubUser;
import com.booking.model.user.Roles;
import com.booking.model.user.UserDto;
import com.booking.service.RoleService;
import com.booking.service.UserService;
import com.booking.uimodel.UIResponse;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;
    


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
    public ClubUser findOne(String username) {
        return userDao.findByUserMobile(username);
    }

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
    	if( userDao.findByUserMobileOrUserEmail(user.getUserMobile(), user.getUserEmail()) !=null) {
    		uiResponse.setStatus("FAILURE");
    		Map<String,String> respone= new HashMap<String, String>();
    		respone.put("response", "User Mobile or Email id Already Exists");
    		uiResponse.setResponse(respone);
    		return new ResponseEntity<>(uiResponse, HttpStatus.OK);
    	}
    	else {
    	
    
    	user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		/*
		 * role.setRoleId(user.getRoleId()); List<Roles> roles = new ArrayList<Roles>();
		 * roles.add(role);
		 */ 
         
        
        
        uiResponse.setStatus("Success");
		uiResponse.setResponse(userDao.save(convertToEntity(user)));
		return new ResponseEntity<>(uiResponse, HttpStatus.OK);
    	}
    	}



	@Override
	public List<ClubUser> findAll() {
		// TODO Auto-generated method stub
		return (List<ClubUser>) userDao.findAll();
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

	
	

	
}
