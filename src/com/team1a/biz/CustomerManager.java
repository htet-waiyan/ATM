package com.team1a.biz;

import java.sql.SQLException;

import com.team1a.dao.CustomerDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dto.Customer;
import com.team1a.util.DuplicateKeyException;

public class CustomerManager {
	private CustomerDAO customerDAO;
	
	{
		customerDAO=DAOFactory.getCustomerDAO();
	}
	
	public CustomerManager(){}
	
//	public RegisterManager(Customer cust){
//		this.customer=cust;
//	}
	
	public int registerCustomer(Customer customer)throws SQLException{
		int rowAffected=0;
		try{
			
			rowAffected=customerDAO.insertCustomer(customer);
			
		}
		
		catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		
		return rowAffected;
	}
	
	public void updateCustomer(Customer s,String searchID)throws SQLException,DuplicateKeyException{
		try{
			customerDAO.updateCustomer(s,searchID);
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	public void deleteCustomer(Customer c){
		try {
			customerDAO.deleteCustomer(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Customer getCustomerById(String id){
			return customerDAO.findCustomerById(id);
	}
}
