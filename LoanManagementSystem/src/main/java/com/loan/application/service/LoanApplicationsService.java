package com.loan.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.application.entity.LoanApplications;
import com.loan.application.exception.ApplicationNotFoundException;
import com.loan.application.exception.MaximumRequestLimitReachedException;
import com.loan.application.repos.LoanApplicationsRepo;

@Service
public class LoanApplicationsService implements InterLoanApplicationsService{

	@Autowired
	private LoanApplicationsRepo loanApplicationsRepository;
	
	@Override
	public LoanApplications createLoanApplication(LoanApplications application) {
		String appName= application.getApplicantName();
		int loanPlanId=application.getLoanPlanId();
		int requestCount = getApplicationsByLoanPlanId(loanPlanId).size();
		int rejectedCount = rejectedLoanApplicationCount(application);
		
		if(rejectedCount>=3)
		{
			throw new MaximumRequestLimitReachedException("Maximum request limit reached as rejected count has gone to: ", rejectedCount);
		}
		
		if (requestCount > 2) {
            throw new MaximumRequestLimitReachedException("Maximum request limit reached for customer: " + appName, requestCount);
        }
        
        return loanApplicationsRepository.save(application);
    }
	
	public int rejectedLoanApplicationCount(LoanApplications application) {
		int count=0;
		List<LoanApplications> loanplans=getApplicationsByLoanPlanId(application.getLoanPlanId());
		for(int i=0;i<loanplans.size();i++)
		{
			if(loanplans.get(i).getApplicationStatus().equals("Rejected"))
				count++;
		}
        return count;
    }
		
	public List<LoanApplications> getApplicationsByLoanPlanId(Integer loanPlanId) {
        return loanApplicationsRepository.findAllByLoanPlanId(loanPlanId);
	}

	@Override
	public List<LoanApplications> getAllNewLoanApplications() {
		return loanApplicationsRepository.findByApplicationStatus("New");
	}

	@Override
	public LoanApplications getLoanApplicationById(int id) {
		return loanApplicationsRepository.findById(id)
		        .orElseThrow(() -> new ApplicationNotFoundException("Loan application not found."));
	}

	@Override
	public LoanApplications respondToLoanApplication(int loanApplicationId, String applicationStatus) {
		LoanApplications loanApplication = loanApplicationsRepository.findById(loanApplicationId)
		        .orElseThrow(() -> new ApplicationNotFoundException("Loan application not found."));

		    loanApplication.setApplicationStatus(applicationStatus);
		    return loanApplicationsRepository.save(loanApplication);
	}

}
