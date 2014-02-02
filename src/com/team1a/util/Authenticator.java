package com.team1a.util;

import java.util.Iterator;
import java.util.List;

import com.team1a.dao.CustomerDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Customer;

public class Authenticator {
	CustomerDAO custDAO;
	
	{
		custDAO=DAOFactory.getCustomerDAO();
	}
	
	public boolean authenticate(String id,String passwd){
		
		Customer c=custDAO.findCustomerById(id);
		boolean b=false;
		
		if(id.equals(c.getUserId())&& passwd.equals(c.getPassword()))
			b=true;
		
		return b;
	}
	
	public boolean validatePIN(List<BankAccount> lst,int pin,int accNo){
		Iterator<BankAccount> itr=lst.iterator();
		
		while(itr.hasNext()){
			BankAccount ba=itr.next();
			
			if(ba.getPinNo()==pin && ba.getAccountNumber()==accNo){
				return true;
			}
		}
		
		return false;
	}
	
}
