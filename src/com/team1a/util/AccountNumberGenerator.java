package com.team1a.util;

import com.team1a.dao.BankAccDAO;
import com.team1a.dao.DAOFactory;

public class AccountNumberGenerator {
	public static synchronized int generate(){
		BankAccDAO bankDAO=DAOFactory.getBankAccDAO();
		
		int accNo=bankDAO.getLastAccNo();
		
		System.out.println(accNo);
		if(accNo==0){
			accNo=10001;
		}
		accNo++;
		
		return accNo;
	}
}
