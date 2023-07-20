package com.loan.application.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.application.entity.DocumentTypes;


public interface DocumentTypesRepo extends JpaRepository<DocumentTypes, Integer>{

}
