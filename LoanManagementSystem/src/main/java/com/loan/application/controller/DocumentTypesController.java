package com.loan.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.application.entity.DocumentTypes;
import com.loan.application.service.DocumentTypesService;
@RequestMapping("/api")
@RestController
public class DocumentTypesController {

	Logger log=LoggerFactory.getLogger(DocumentTypesController.class);
	
	 @Autowired
	 private DocumentTypesService docTypeService;
	 
	 /*
	 @GetMapping(path="/hello")
	 public String hello()
	 {
		 System.out.println("Hello");
		 return "Hello";
	 }
	 */
	 
	@GetMapping(path="/documenttypes", produces= {MediaType.APPLICATION_JSON_VALUE})
	 public ResponseEntity<List<DocumentTypes>> getAllDocumentTypes()
	 {
		 List<DocumentTypes> docTypes=docTypeService.getAllDocumentTypes();
		 log.info("All the documents fetched successfully");
		 return new ResponseEntity<List<DocumentTypes>>(docTypes, HttpStatus.OK);
	 }
}
