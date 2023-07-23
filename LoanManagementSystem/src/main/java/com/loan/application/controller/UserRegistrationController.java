package com.loan.application.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.application.dto.UserRegistrationDto;
import com.loan.application.entity.User;
import com.loan.application.service.UserService;

@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class UserRegistrationController {
    
    @Autowired
    private UserService userService;
    @Autowired
	private ModelMapper modelMapper;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto userDto) throws Exception {
        String tempEmailId = userDto.getEmail();
        User userObj = userService.fetchUserByEmail(tempEmailId);
        if(userObj == null || tempEmailId == null || "".equals(tempEmailId)) {
            throw new Exception("User with "+tempEmailId+" is already exist");
        }
        User userDto2 = userService.saveUser(userObj, userDto.getName());
        return new ResponseEntity<User>(userDto2, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@ModelAttribute UserRegistrationDto userDto) throws Exception {
        String tempEmailId = userDto.getEmail();
        String tempPass = userDto.getPassword();
        User userObj = null;
        if(tempEmailId != null && tempPass != null) {
            userObj = userService.fetchUserByEmailAndPassword(tempEmailId, tempPass);
        }
        if(userObj == null) {
            throw new Exception("Bad credentials");
        }
        return new ResponseEntity<User>(userObj, HttpStatus.OK);
    }

    
}