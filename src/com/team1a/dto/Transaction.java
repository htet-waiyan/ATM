package com.team1a.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	private int txnID;
	private BankAccount bankaccount;
	private String txnType;
	private BigDecimal txnAmount;
	private int toAccount;
	private Date txnTime;
	
	public Transaction(){}

	public Transaction(int txnID, BankAccount bankaccount, String txnType,
			BigDecimal txnAmount, int toAccount, Date txnTime) {
		super();
		this.txnID = txnID;
		this.bankaccount = bankaccount;
		this.txnType = txnType;
		this.txnAmount = txnAmount;
		this.toAccount = toAccount;
		this.txnTime = txnTime;
	}

	public int getTxnID() {
		return txnID;
	}

	public void setTxnID(int txnID) {
		this.txnID = txnID;
	}

	public BankAccount getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(BankAccount bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public Date getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(Date txnTime) {
		this.txnTime = txnTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankaccount == null) ? 0 : bankaccount.hashCode());
		result = prime * result + toAccount;
		result = prime * result
				+ ((txnAmount == null) ? 0 : txnAmount.hashCode());
		result = prime * result + txnID;
		result = prime * result + ((txnTime == null) ? 0 : txnTime.hashCode());
		result = prime * result + ((txnType == null) ? 0 : txnType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (bankaccount == null) {
			if (other.bankaccount != null)
				return false;
		} else if (!bankaccount.equals(other.bankaccount))
			return false;
		if (toAccount != other.toAccount)
			return false;
		if (txnAmount == null) {
			if (other.txnAmount != null)
				return false;
		} else if (!txnAmount.equals(other.txnAmount))
			return false;
		if (txnID != other.txnID)
			return false;
		if (txnTime == null) {
			if (other.txnTime != null)
				return false;
		} else if (!txnTime.equals(other.txnTime))
			return false;
		if (txnType == null) {
			if (other.txnType != null)
				return false;
		} else if (!txnType.equals(other.txnType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [txnID=" + txnID + ", bankaccount=" + bankaccount
				+ ", txnType=" + txnType + ", txnAmount=" + txnAmount
				+ ", toAccount=" + toAccount + ", txnTime=" + txnTime + "]";
	}
	
	
}
