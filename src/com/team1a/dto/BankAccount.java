package com.team1a.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount implements Serializable {
	
	protected int accountNumber;
	protected int pinNo;
	protected BigDecimal balance;
	protected Date createDate=null;
	protected List<Customer> holderList=new ArrayList<Customer>();
	protected BankBranch branch;
	protected String type;
	
	public BankAccount() {
		super();
	}

	public BankAccount(int accountNumber, int pinNo) {
		super();
		this.accountNumber = accountNumber;
		this.pinNo = pinNo;
	}
	
	public BankBranch getBranch() {
		return branch;
	}

	public void setBranch(BankBranch branch) {
		this.branch = branch;
	}

	public void addHolder(Customer c){
		holderList.add(c);
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Customer> getHolderList() {
		return holderList;
	}

	public void setHolderList(List<Customer> holderList) {
		this.holderList = holderList;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPinNo() {
		return pinNo;
	}

	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + pinNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (pinNo != other.pinNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", pinNo="
				+ pinNo + ", balance=" + balance + "]";
	}

}
