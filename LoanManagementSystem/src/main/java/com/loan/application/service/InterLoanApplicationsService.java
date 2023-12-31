package com.loan.application.service;

import java.util.List;

import com.loan.application.entity.LoanApplications;

public interface InterLoanApplicationsService {

	public LoanApplications createLoanApplication(LoanApplications application);
	
	public List<LoanApplications> getAllNewLoanApplications();
	
	public LoanApplications getLoanApplicationById(int id);

	public LoanApplications respondToLoanApplication(int loanApplicationId, String applicationStatus);

	public List<LoanApplications> getAllApprovedLoanApplications();

	public List<LoanApplications> getAllRejectedLoanApplications();

	public List<LoanApplications> getAllLoanApplications();
	
	public List<LoanApplications> getApplicationByEmail(String applicantEmail);
}
