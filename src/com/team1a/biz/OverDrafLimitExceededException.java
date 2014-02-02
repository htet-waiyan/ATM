package com.team1a.biz;

import com.team1a.util.ExceedLimitException;
import com.team1a.util.TransactionAbortedException;

public class OverDrafLimitExceededException extends ExceedLimitException {

	public OverDrafLimitExceededException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OverDrafLimitExceededException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
