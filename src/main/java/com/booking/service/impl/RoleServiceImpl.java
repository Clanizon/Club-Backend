package com.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.RoleDao;
import com.booking.model.user.Roles;
import com.booking.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Roles findByName(String name) {
        Roles role = roleDao.findByName(name);
        return role;
    }
}
