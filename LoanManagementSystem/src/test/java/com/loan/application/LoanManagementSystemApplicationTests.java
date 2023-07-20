package com.loan.application;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.loan.application.entity.*;
import com.loan.application.repos.DocumentTypesRepo;
import com.loan.application.repos.LoanApplicationsRepo;
import com.loan.application.service.DocumentTypesService;
import com.loan.application.service.LoanApplicationsService;

@SpringBootTest
class LoanApplicationSystemApplicationTests {
	
	
	@InjectMocks
	private LoanApplicationsService loanApplicationsService;

	@InjectMocks
	private DocumentTypesService documentTypesService;

	@Mock
	private LoanApplicationsRepo loanApplicationsRepository;

	@Mock
	private DocumentTypesRepo documentTypesRepository;
	 

	@Test
	public void getAllNewLoanApplicationsTest(){
		
		 List<LoanApplications> loanApplications = new ArrayList<>();
		    
		    when(loanApplicationsRepository.findAll()).thenReturn(loanApplications);

		    List<LoanApplications> result = loanApplicationsService.getAllNewLoanApplications();

		    assertEquals(loanApplications, result);
	}

	@Test
	public void getLoanApplicationByIdTest() throws Exception{
		int loanApplicationId = 1;
	    LoanApplications loanApplication = new LoanApplications();
	    
	    when(loanApplicationsRepository.findById(loanApplicationId)).thenReturn(Optional.of(loanApplication));

	    LoanApplications result = loanApplicationsService.getLoanApplicationById(loanApplicationId);

	    assertEquals(loanApplication, result);
	}

	@Test
	public void createLoanApplicationTest() {
		LoanApplications loanApplication = new LoanApplications();
	    
	    when(loanApplicationsRepository.save(any(LoanApplications.class))).thenReturn(loanApplication);

	    LoanApplications result = loanApplicationsService.createLoanApplication(loanApplication);

	    assertEquals(loanApplication, result);
	}

	
	 @Test
	 public void testRespondToLoanApplication_Success() {
		    int loanApplicationId = 1;
		    String applicationStatus = "Approved";
		    LoanApplications loanApplication = new LoanApplications();
		    
		    when(loanApplicationsRepository.findById(loanApplicationId)).thenReturn(Optional.of(loanApplication));
		    when(loanApplicationsRepository.save(any(LoanApplications.class))).thenReturn(loanApplication);

		    LoanApplications result = loanApplicationsService.respondToLoanApplication(loanApplicationId, applicationStatus);

		    assertEquals(applicationStatus, result.getApplicationStatus());
	 }
	@Test
	public void getAllDocumentTypesTest(){
		
		List<DocumentTypes> documentTypes = new ArrayList<>();
		
	    when(documentTypesRepository.findAll()).thenReturn(documentTypes);

	    List<DocumentTypes> result = documentTypesService.getAllDocumentTypes();

	    assertEquals(documentTypes, result);
	    
	}
	
	

}