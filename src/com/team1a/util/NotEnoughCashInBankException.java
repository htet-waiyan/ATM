package com.team1a.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NotEnoughCashInBankException extends TransactionAbortedException {
	private BigDecimal current;
	private BigDecimal transfer;
	
	public NotEnoughCashInBankException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public NotEnoughCashInBankException(BigDecimal current, BigDecimal transfer,String msg) {
		super(msg);
		this.current = current;
		this.transfer = transfer;
	}


	public NotEnoughCashInBankException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, Double> getStatus(){
		double curAmt=current.doubleValue();
		double trAmt=current.doubleValue();
		
		Map<String, Double> status=new HashMap<>();
		status.put("Current", curAmt);
		status.put("Transfer", trAmt);
		
		return status;
	}
}
