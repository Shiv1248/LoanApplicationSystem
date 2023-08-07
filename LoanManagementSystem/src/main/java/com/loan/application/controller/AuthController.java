package com.loan.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.application.entity.JwtRequest;
import com.loan.application.entity.JwtResponse;
import com.loan.application.entity.User;
import com.loan.application.repos.UserRepo;
import com.loan.application.entity.Role;
import com.loan.application.security.JwtHelper;
import com.loan.application.service.CustomUserDetailsService;
import com.loan.application.service.RoleService;
import com.loan.application.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private JwtHelper helper;

    @Autowired
    private CustomUserDetailsService custom;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUsername(userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
        	manager.authenticate(authentication);
           
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user, "Customer");
    }    

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /*@GetMapping("/role/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.findByName(name);
    }
    */
    
    @GetMapping("/role/{email}")
    public String getRoleByEmail(@PathVariable String email)
    {
    	User u1=userRepo.findByEmail(email);
    	Role role=roleService.findByName(u1.getName());
    	return role.getName();
    }

    @GetMapping("/user/{email}")    
    public UserDetails getUserByEmail(@PathVariable String email) {
        return custom.loadUserByUsername(email);
    }

}
