package com.loan.application.exception;

public class MaximumRequestLimitReachedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxRequestLimit;

    public MaximumRequestLimitReachedException(String message, int maxRequestLimit) {
        super(message);
        this.maxRequestLimit = maxRequestLimit;
    }

    public int getMaxRequestLimit() {
        return maxRequestLimit;
    }
}