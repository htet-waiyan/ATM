package com.team1a.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CheckingAccount extends BankAccount implements Serializable {
	private BigDecimal overdraftAmount;
	//super.type="Checking";

	public CheckingAccount() {
		super();
	}

	public CheckingAccount(int accountNumber, int pinNo) {
		super(accountNumber, pinNo);
		// TODO Auto-generated constructor stub
	}
	
	public CheckingAccount(int accountNumber, int pinNo,BigDecimal overdraft_amt) {
		super(accountNumber, pinNo);
		this.overdraftAmount=overdraft_amt;
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(BigDecimal overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}

	@Override
	public String toString() {
		return "CheckingAccount [overdraftAmount=" + overdraftAmount + "]";
	}
	
	
	
}
