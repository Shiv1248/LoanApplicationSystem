package com.loan.application.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.application.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}