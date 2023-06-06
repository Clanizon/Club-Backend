package com.booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.booking.model.user.ClubUser;
import com.booking.model.user.UserDto;
import com.booking.uimodel.UIResponse;

public interface UserService {
    ClubUser save(UserDto user);
    Object updatePassword(UserDto user);
    ClubUser saveorupdate(UserDto user);
    List<ClubUser> findAll();
    ClubUser findOne(String username);
	Object listUser(UserDto user);
	ResponseEntity<UIResponse> checkandsave(UserDto user);
	
}
