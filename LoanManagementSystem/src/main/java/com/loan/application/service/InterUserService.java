package com.loan.application.service;

import java.util.List;
import java.util.Optional;

import com.loan.application.entity.User;

public interface InterUserService {

	User saveUser(User user, String name);
    User fetchUserByEmail(String email);
    List<User> getAllUsers();
}
