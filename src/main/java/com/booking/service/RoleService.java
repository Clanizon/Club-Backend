package com.booking.service;

import com.booking.model.user.Roles;

public interface RoleService {
    Roles findByName(String name);
}
