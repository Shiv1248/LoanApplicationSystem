package com.loan.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.application.entity.DocumentTypes;
import com.loan.application.repos.DocumentTypesRepo;

@Service
public class DocumentTypesService implements InterDocumentTypesService{

	@Autowired
	private DocumentTypesRepo documentTypesRepository;
	
	@Override
	public List<DocumentTypes> getAllDocumentTypes() {
		return documentTypesRepository.findAll();
	}

}
