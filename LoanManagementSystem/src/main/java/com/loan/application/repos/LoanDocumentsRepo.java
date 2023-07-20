package com.loan.application.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loan.application.entity.LoanDocuments;


public interface LoanDocumentsRepo extends JpaRepository<LoanDocuments, Integer>{

}
