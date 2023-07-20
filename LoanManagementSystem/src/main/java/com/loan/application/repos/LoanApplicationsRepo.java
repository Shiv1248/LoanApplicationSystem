package com.loan.application.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loan.application.entity.LoanApplications;


public interface LoanApplicationsRepo extends JpaRepository<LoanApplications, Integer> {

	@Query("SELECT la FROM LoanApplications la WHERE la.LoanPlanId = :n")
List<LoanApplications> findAllByLoanPlanId(@Param("n") Integer loanPlanId);

@Query("SELECT la FROM LoanApplications la WHERE la.ApplicationStatus = :string")
List<LoanApplications> findByApplicationStatus(@Param("string") String applicationStatus);

}