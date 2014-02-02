package com.team1a.dao;

import com.team1a.dto.BankBranch;

public class DAOFactory {

	public static CustomerDAO getCustomerDAO() {
		return new CustomerDAOImpl();
	}
	
	public static BankAccDAO getBankAccDAO(){
		return new BankAccDAOImpl();
	}
	
	public static BankBranchDAO getBankBranchDAO(){
		return new BankBranchDAOImpl();
	}
}
