package com.loan.application.service;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loan.application.entity.User;
import com.loan.application.repos.RoleRepo;
import com.loan.application.repos.UserRepo;
import com.loan.application.entity.Role;

@Service
public class UserService implements InterUserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setName("Admin");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setName("User");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setEmail("admin@edu");
        adminUser.setPassword(bcryptEncoder.encode("admin"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepo.save(adminUser);
    }

    @Override
    public User saveUser(User user, String name ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findRoleByName(name);
        if (role == null) {
            throw new IllegalArgumentException("Invalid role name: " + name);
        }

        user.setRoles(Collections.singleton(role));
        return userRepo.save(user);
    }


    @Override
    public User fetchUserByEmail(String email) {
        // TODO Auto-generated method stub
    	User user=userRepo.findByEmail(email);
    	
    	return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().forEach(e -> list.add(e));
        return list;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

	public User fetchUserByEmailAndPassword(String tempEmailId, String tempPass) {
		// TODO Auto-generated method stub
		User user=userRepo.findByEmailandPassword(tempEmailId, tempPass);
    	
    	return user;
	}
}

