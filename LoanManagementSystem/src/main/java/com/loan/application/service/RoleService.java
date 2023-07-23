package com.loan.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.application.entity.Role;
import com.loan.application.repos.RoleRepo;

@Service
public class RoleService implements InterRoleService {
    @Autowired
    private RoleRepo roleRepo;

    private RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleRepo.findRoleByName(name);
        System.out.println("Role: " + role);
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    
}
