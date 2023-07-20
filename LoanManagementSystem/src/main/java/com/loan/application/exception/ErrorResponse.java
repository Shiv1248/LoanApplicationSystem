package com.loan.application.exception;

public class ErrorResponse {
	
	 private String errorMessage;
	 private int errorCode;

	 public ErrorResponse(String errorMessage, int maxRequestLimit) {
	  super();
	  this.errorMessage = errorMessage;
	  this.errorCode = maxRequestLimit;
	 }

	 public String getErrorMessage() {
	  return errorMessage;
	 }

	 public int getErrorCode() {
	  return errorCode;
	 }
	}
