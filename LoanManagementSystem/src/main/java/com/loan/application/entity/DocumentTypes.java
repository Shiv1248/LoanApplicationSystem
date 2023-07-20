package com.loan.application.entity;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class DocumentTypes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private String DocumentType;
	
	@OneToMany(mappedBy="doctype", cascade=CascadeType.ALL)
	private List<LoanDocuments> loandocs=new ArrayList<>();
}

