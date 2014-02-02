package com.team1a.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.team1a.dto.BankAccount;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.SavingAccount;
import com.team1a.dto.Transaction;
import com.team1a.util.NoAccountFound;
import com.team1a.util.NotEnoughCashInBankException;
import com.team1a.util.TransactionAbortedException;
import com.team1a.util.TxnType;

public interface BankAccDAO {
	public java.util.List<Transaction> getTransactionHistory(BankAccount ba)
			throws SQLException;

//	public List<Transaction> getTransactionHistorybyDate(int BankAccountNumber,
//			Date StartDate, Date EndDate) throws SQLException;

	public int createCheckingAccount(CheckingAccount checkingAcc,
			String holderAccNo) throws SQLException;

	public void updateCheckingAccount(int pin, BigDecimal over, int acc)
			throws SQLException;

	public void deleteCheckingAccount(int acc) throws SQLException;

	public int createSavingAccount(SavingAccount savingAcc, String holderID)
			throws SQLException;

	public void updateSavingAccount(int pin, BigDecimal min, int acc)
			throws SQLException;

	public void deleteSavingAccount(int acc) throws SQLException;

	public Map<Integer, BankAccount> getAllAccountsOf(String userId)
			throws SQLException;

	public void insertTransaction(Transaction txn, BigDecimal toNewBalance)
			throws SQLException;

	public BigDecimal getCurrentBalanceOf(int accNo) throws SQLException;

	public void insertTransaction(Transaction txn) throws SQLException;
	
	public java.util.List<Transaction> getTransactionHistory(int accNo)
			throws SQLException;

	public int getLastAccNo();
}
