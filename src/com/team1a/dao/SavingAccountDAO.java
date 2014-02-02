package com.team1a.dao;

import java.sql.SQLException;

import com.team1a.dto.SavingAccount;

public interface SavingAccountDAO {
	public int createAccount(SavingAccount savingAcc,String holderID)throws SQLException;
	
	public void updateAccount(SavingAccount sa)throws SQLException;
	
	public void deleteAccount(SavingAccount sa)throws SQLException;
	
	public int getLastAccNo();
}
