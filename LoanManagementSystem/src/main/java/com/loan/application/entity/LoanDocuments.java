package com.loan.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LoanDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String DocumentURL;
	private int LoanApplicationId;
	
	@ManyToOne
	private LoanApplications loanapp;
	
	@ManyToOne
	private DocumentTypes doctype;
	
}