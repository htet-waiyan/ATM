package com.team1a.controller;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.team1a.biz.BankAccountManager;
import com.team1a.biz.CustomerManager;
import com.team1a.dao.CustomerDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.Customer;
import com.team1a.dto.SavingAccount;
import com.team1a.util.BelowMinimumBalanceException;
import com.team1a.util.DuplicateKeyException;
import com.team1a.util.ExceedLimitException;

public class UpdateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Customer c=new Customer();
//		c.setName("Jackie");
//		c.setNric("M004");
//		c.setAddress("Yuo Chu Khang");
//		c.setPassword("345");
//		c.setUserId("A005");
//		
//		CustomerDAO cd=DAOFactory.getCustomerDAO();
//		BankAccountManager mn=new BankAccountManager();
//		
//		SavingAccount sa=new SavingAccount();
//		sa.setPinNo(123456);
//		sa.setBalance(new BigDecimal(0.0));
//		sa.setMinimumBalance(new BigDecimal(40));
//		
//		CheckingAccount ca=new CheckingAccount();
//		ca.setPinNo(345678);
//		ca.setBalance(new BigDecimal(0.0));
//		ca.setOverdraftAmount(new BigDecimal(-200));
//		
//		CustomerManager cm=new CustomerManager();
//		
//		try{
////			cd.updateCustomer(c);
////			System.out.println("Update Successful");
//			
//			//mn.createCheckingAccount(ca, c.getUserId());
//			cm.updateCustomer(c,"A004");
//			System.out.println("Update Successful");
//		}
//		catch(DuplicateKeyException de){
//			de.printStackTrace();
//		}
//		
//		catch(SQLException e){
//			e.printStackTrace();
//		}
		try{
			throw new BelowMinimumBalanceException();
		}
		catch(ExceedLimitException e){
			e.printStackTrace();
			Throwable th=e.getCause();
			
			if(th!=null){
				if(th instanceof BelowMinimumBalanceException)
					System.err.println("1 below");
				
				else{
					Throwable t=th.getCause();
				
					if(t instanceof BelowMinimumBalanceException)
						System.err.println("below minimum exception");
				}
			}
			
			//System.err.println("Exception");
		}
	}

}
