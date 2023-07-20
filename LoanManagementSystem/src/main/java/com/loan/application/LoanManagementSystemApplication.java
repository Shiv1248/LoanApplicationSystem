package com.loan.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @ComponentScan(basePackages = {"main.controller", "main.service"})
// @EnableJpaRepositories("main.repos")
public class LoanManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementSystemApplication.class, args);
		System.out.println("Hi Shivansh! This application is running!");
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
