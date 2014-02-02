package com.team1a.biz;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.team1a.dao.BankAccDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dto.BankAccount;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.SavingAccount;
import com.team1a.dto.Transaction;
import com.team1a.util.BelowMinimumBalanceException;
import com.team1a.util.ExceedLimitException;
import com.team1a.util.NoAccountFound;
import com.team1a.util.NotEnoughCashInBankException;
import com.team1a.util.TransactionAbortedException;

public class TxnManager {
	
	private BankAccDAO bankDAO=DAOFactory.getBankAccDAO();
	private Transaction txn=null;
	private BigDecimal fromNewBalance=null;
	private BigDecimal toNewBalance=null;
	private SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public void transferTo(BankAccount from,Transaction txn)throws NoAccountFound,NotEnoughCashInBankException,ExceedLimitException{
		
		
		if(from instanceof CheckingAccount){
			CheckingAccount ca= (CheckingAccount) from;
			
			transferToCheckingAcc(ca,txn);
		}
		else if (from instanceof SavingAccount){
			SavingAccount sa= (SavingAccount) from;
			
			transferToSavingAcc(sa,txn);
		}
	}
	
	private void transferToCheckingAcc(CheckingAccount ca,Transaction txn)throws NoAccountFound, NotEnoughCashInBankException,OverDrafLimitExceededException{
		if(ca.getBalance().doubleValue()<txn.getTxnAmount().doubleValue()){
			throw new NotEnoughCashInBankException("You don't have enought balance to transfer");
		}
		
		else{
			//fromNewBalance=ca.getBalance().doubleValue()-txn.getTxnAmount().doubleValue();
			fromNewBalance=ca.getBalance().subtract(txn.getTxnAmount());
			
			if(fromNewBalance.compareTo(ca.getOverdraftAmount())==-1){
				throw new OverDrafLimitExceededException("Transfer Not Allowed. You exceeding overdraft Amount");
			}
			else{
				try {
					toNewBalance=bankDAO.getCurrentBalanceOf(txn.getToAccount());
					
					toNewBalance=toNewBalance.add(txn.getTxnAmount());
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				catch(NullPointerException ne){
					ne.printStackTrace();
					throw new NoAccountFound("The account you're transfering doesn't exist.");
				}
				ca.setBalance(fromNewBalance);
//				txn=new Transaction();
//				txn.setBankaccount(ca);
//				txn.setToAccount(txn.getToAccount());
//				txn.setTxnAmount(txn.getTxnAmount());
//				
//				Date txnTime=Calendar.getInstance().getTime();
//				
//				try {
//					txn.setTxnTime(fmt.parse(fmt.format(txnTime)));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				txn.setTxnType("Transfer");		
				try {
					bankDAO.insertTransaction(txn,toNewBalance);
					ca.setBalance(fromNewBalance);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(e.getErrorCode()==1452)
						throw new NoAccountFound("The account you're transfering doesn't exist.");
				}
			}
		}
	}
	
	private void transferToSavingAcc(SavingAccount sa,Transaction txn)throws NoAccountFound,NotEnoughCashInBankException,ExceedLimitException{
		if(sa.getBalance().doubleValue()<txn.getTxnAmount().doubleValue()){
			throw new NotEnoughCashInBankException("You don't have enought balance to transfer");
		}
		
		else{
			//fromNewBalance=sa.getBalance().doubleValue()-txn.getTxnAmount().doubleValue();
			fromNewBalance=sa.getBalance().subtract(txn.getTxnAmount());
			
			if(fromNewBalance.compareTo(sa.getMinimumBalance())==-1){
				throw new BelowMinimumBalanceException("Transfer Not Allowed. You exceeded  minimum balance");
			}
			else{
				try {
					toNewBalance=bankDAO.getCurrentBalanceOf(txn.getToAccount());
					toNewBalance=toNewBalance.add(txn.getTxnAmount());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch(NullPointerException ne){
					ne.printStackTrace();
					throw new NoAccountFound("The account you're transfering doesn't exist.");
				}
				sa.setBalance(fromNewBalance);
//				txn=new Transaction();
//				txn.setBankaccount(sa);
//				txn.setToAccount(txn.getToAccount());
//				txn.setTxnAmount(txn.getTxnAmount());
//				
//				Date txnTime=Calendar.getInstance().getTime();
//				
//				try {
//					txn.setTxnTime(fmt.parse(fmt.format(txnTime)));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				txn.setTxnType("Transfer");
				try {
					bankDAO.insertTransaction(txn,toNewBalance);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					if(e.getErrorCode()==1452)
						throw new NoAccountFound("The account you're transfering doesn't exist.");
				}
			}
		}
	}
	
	public void deposit(Transaction txn){
		BigDecimal curBal=null;;
		try {
			curBal = bankDAO.getCurrentBalanceOf(txn.getBankaccount().getAccountNumber());
			//txn.getBankaccount().setBalance(new BigDecimal(curBal.doubleValue()+txn.getTxnAmount().doubleValue()));
			txn.getBankaccount().setBalance(curBal.add(txn.getTxnAmount()));
			
			bankDAO.insertTransaction(txn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void withdraw(Transaction txn)throws NotEnoughCashInBankException,ExceedLimitException{
		BigDecimal curBal=null;
		//double newBalance=0.0;
		BigDecimal newBalance=null;
		
		try{
			curBal = bankDAO.getCurrentBalanceOf(txn.getBankaccount().getAccountNumber());
			
			
			if(txn.getBankaccount() instanceof SavingAccount){
				SavingAccount sa=(SavingAccount) txn.getBankaccount();
				
				if(txn.getTxnAmount().compareTo(sa.getBalance())==1){
					throw new NotEnoughCashInBankException("You don't have enough cash to withdraw");
				}
				
				else{
					//newBalance=curBal.doubleValue()-txn.getTxnAmount().doubleValue();
					newBalance=curBal.subtract(txn.getTxnAmount());
					
					if(newBalance.compareTo(sa.getMinimumBalance())==-1){
						throw new BelowMinimumBalanceException("Cannot withdraw. You're exceeding minimum balance");
					}
					else{
						txn.getBankaccount().setBalance(newBalance);
						bankDAO.insertTransaction(txn);
					}
				}
			}
			else if (txn.getBankaccount() instanceof CheckingAccount){
				CheckingAccount ca=(CheckingAccount) txn.getBankaccount();
				
				//newBalance=curBal.doubleValue()-txn.getTxnAmount().doubleValue();
				newBalance=curBal.subtract(txn.getTxnAmount());
				
				if(newBalance.compareTo(ca.getOverdraftAmount())==-1){
					throw new OverDrafLimitExceededException("Cannot withdraw. You're exceeding overdraft amount");
				}
				else{
					txn.getBankaccount().setBalance(newBalance);
					bankDAO.insertTransaction(txn);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<Transaction> getTransactionHistory(BankAccount ba){
		try {
			return bankDAO.getTransactionHistory(ba);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Transaction> getTransactionHistory(int id){
		try {
			return bankDAO.getTransactionHistory(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
