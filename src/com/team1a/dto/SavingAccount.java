package com.team1a.dto;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount {
	private BigDecimal minimumBalance;
	private final String type="Saving";

	public SavingAccount() {
		super();
	}

	
	public String getType() {
		return type;
	}


	public SavingAccount(int accountNumber, int pinNo) {
		super(accountNumber, pinNo);
		// TODO Auto-generated constructor stub
	}
	
	public SavingAccount(int accountNumber, int pinNo,BigDecimal min_bal) {
		super(accountNumber, pinNo);
		this.minimumBalance=min_bal;
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(BigDecimal minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public String toString() {
		return super.toString()+"SavingAccount [minimumBalance=" + minimumBalance + "]";
	}
	
	
}
