package com.booking.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.user.ClubConfig;


@Repository
public interface ClubConfigDao extends CrudRepository<ClubConfig, Long> {

	ClubConfig findByKey(String key);
	ClubConfig findByConfigId(String key);
	
	
}