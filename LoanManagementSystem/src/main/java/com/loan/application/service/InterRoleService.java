package com.loan.application.service;

import java.util.List;

import com.loan.application.entity.Role;

public interface InterRoleService {

	 Role findByName(String name);
	    List<Role> getAllRoles();
}
