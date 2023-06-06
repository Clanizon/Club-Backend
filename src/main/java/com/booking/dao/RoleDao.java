package com.booking.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.user.Roles;

@Repository
public interface RoleDao extends CrudRepository<Roles, Long> {
    Roles findByName(String name);
}