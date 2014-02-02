package com.team1a.dao;


import java.sql.SQLException;
import com.team1a.dto.Customer;

public interface CustomerDAO {
	
	public int insertCustomer(Customer s) throws SQLException ;
	
	public int updateCustomer(Customer s,String searchID) throws SQLException;
	
	public void deleteCustomer(Customer s) throws SQLException;
	
	public Customer findCustomerById(String id);
}
