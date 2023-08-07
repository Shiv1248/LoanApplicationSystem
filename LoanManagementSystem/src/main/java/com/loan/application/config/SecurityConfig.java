package com.loan.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.loan.application.security.JwtAuthenticationEntryPoint;
import com.loan.application.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/home/**").permitAll()
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/register").permitAll()
                                .requestMatchers("/api/documenttypes").permitAll()
                                .requestMatchers("/api/loanapplications").permitAll()
                                .requestMatchers("/api/loanapplications/new").permitAll()
                                .requestMatchers("/api/loanapplications/approved").permitAll()
                                .requestMatchers("/api/loanapplications/rejected").permitAll()
                                .requestMatchers("/api/loanapplication").permitAll()
                                .requestMatchers("/api/loanapplications/{applicationId}").permitAll()
                                .requestMatchers("/api/loanapplication/{applicationId}").permitAll()
                                .requestMatchers("/api/loanapplication/{applicantEmail}").permitAll()
                                .requestMatchers("/api/registration").permitAll()
                                .requestMatchers("/api/users").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);        
        return http.build();        
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}