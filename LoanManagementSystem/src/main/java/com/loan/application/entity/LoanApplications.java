package com.loan.application.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class LoanApplications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ApplicationId;

	@NotNull
	@Length(min = 10, message = "Applicant name must be at least 10 characters long")
	private String ApplicantName;
	private String ApplicantAddress;
	private String ApplicantProfession;

	@NotNull(message = "contact cannot be null")
	@Length(min = 10, max = 10, message = "invalid contact number")
	private String ApplicantPhone;
	private String ApplicantEmail;
	private String ApplicantPAN;
	private int MonthlyIncome;

	@Min(value = 0)
	private int NoOfDependents;
	private LocalDate ApplicationDate;
	private int LoanPlanId;

	@NotNull
	@Pattern(regexp = "New|Approved|Rejected", message = "invalid status")
	private String ApplicationStatus;
	private LocalDate ApplicationReviewedOn;

	@OneToMany(mappedBy = "loanapp", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<LoanDocuments> loandocs = new ArrayList<>();

}


