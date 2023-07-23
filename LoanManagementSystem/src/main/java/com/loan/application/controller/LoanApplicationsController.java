package com.loan.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loan.application.entity.LoanApplications;
import com.loan.application.service.LoanApplicationsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoanApplicationsController {
   
	@Autowired
	private LoanApplicationsService loanApplicationService;

    public LoanApplicationsController(LoanApplicationsService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/loanapplications")
    public ResponseEntity<List<LoanApplications>> getAllLoanApplications() {
        List<LoanApplications> loan=loanApplicationService.getAllLoanApplications();
        return new ResponseEntity<List<LoanApplications>>(loan, HttpStatus.OK);
    }
    
    @GetMapping("/loanapplications/new")
    public ResponseEntity<List<LoanApplications>> getAllNewLoanApplications() {
        List<LoanApplications> loan=loanApplicationService.getAllNewLoanApplications();
        return new ResponseEntity<List<LoanApplications>>(loan, HttpStatus.OK);
    }
    
    @GetMapping("/loanapplications/approved")
    public ResponseEntity<List<LoanApplications>> getAllApprovedLoanApplications() {
        List<LoanApplications> loan=loanApplicationService.getAllApprovedLoanApplications();
        return new ResponseEntity<List<LoanApplications>>(loan, HttpStatus.OK);
    }
    
    @GetMapping("/loanapplications/rejected")
    public ResponseEntity<List<LoanApplications>> getAllRejectedLoanApplications() {
        List<LoanApplications> loan=loanApplicationService.getAllRejectedLoanApplications();
        return new ResponseEntity<List<LoanApplications>>(loan, HttpStatus.OK);
    }
    
    @PostMapping("/loanapplication")
    @ResponseBody
    public ResponseEntity<LoanApplications> createLoanApplication(@RequestBody LoanApplications loanApplication) {
        LoanApplications loan=loanApplicationService.createLoanApplication(loanApplication);
        return new ResponseEntity<LoanApplications>(loan, HttpStatus.CREATED);
    }

    @GetMapping("/loanapplications/{applicationId}")
    public ResponseEntity<LoanApplications> getLoanApplicationById(@PathVariable Integer applicationId) {
    	LoanApplications loan=loanApplicationService.getLoanApplicationById(applicationId);
        return new ResponseEntity<LoanApplications>(loan, HttpStatus.OK);
    }

    @PutMapping("/loanapplication/{applicationId}")
    @ResponseBody
    public ResponseEntity<LoanApplications> respondToLoanApplication(
            @PathVariable Integer applicationId,
            @RequestBody LoanApplications app) 
    {
    	LoanApplications loan= loanApplicationService.respondToLoanApplication(applicationId, app.getApplicationStatus());
        return new ResponseEntity<LoanApplications>(loan, HttpStatus.ACCEPTED);
    }
}
