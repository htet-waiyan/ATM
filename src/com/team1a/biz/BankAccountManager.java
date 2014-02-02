package com.team1a.biz;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.team1a.dao.BankAccDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dao.SavingAccountDAO;
import com.team1a.dto.BankAccount;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.SavingAccount;
import com.team1a.util.NoAccountFound;

public class BankAccountManager {
	//Saving Account DAO
//	private SavingAccountDAO saDAO;
//	
//	//Checking Account DAO
//	private CheckingAccountDAO caDAO;
	
	private BankAccDAO bankDAO=null;
	
	{
		bankDAO=DAOFactory.getBankAccDAO();
	}
	public int createSavingAccount(SavingAccount sa,String holderAcc)throws SQLException{
		try{
			int row=bankDAO.createSavingAccount(sa, holderAcc);
			
			return row;
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
	
	public void updateSavingAccount(int pin,BigDecimal min,int acc){
		//saDAO.up
		try{
			bankDAO.updateSavingAccount(pin, min, acc);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteSavingAccount(int acc){
		try {
			bankDAO.deleteSavingAccount(acc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCheckingAccount(int acc){
		try {
			bankDAO.deleteCheckingAccount(acc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCheckingAccount(int pin,BigDecimal min,int acc){
		//saDAO.up
		try{
			bankDAO.updateCheckingAccount(pin, min, acc);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void createCheckingAccount(CheckingAccount ca,String holderID)throws SQLException{
		try {
			bankDAO.createCheckingAccount(ca, holderID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
	
	public Map<Integer,BankAccount> getAllAccountsOf(String userId){
		try {
			return bankDAO.getAllAccountsOf(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public BigDecimal getCurrentBalanceOf(int accNo)throws NoAccountFound, SQLException{
		BigDecimal cur=bankDAO.getCurrentBalanceOf(accNo);
		
		if(cur==null)
			throw new NoAccountFound("No Account Found To Transfer");
		
		return cur;
	}
	public int getAccNo(){
		int last=bankDAO.getLastAccNo();
		return ++last;
	}
}
