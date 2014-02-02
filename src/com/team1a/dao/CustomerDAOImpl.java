package com.team1a.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.team1a.dto.Customer;
import com.team1a.util.DuplicateKeyException;

public class CustomerDAOImpl implements CustomerDAO {
	Connection conn;
	
	public int insertCustomer(Customer c) throws SQLException {
		openConnection();
		//st = conn.createStatement();
		
		/*st= conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		int result = st.executeUpdate("INSERT INTO bank.customer (userId, NRIC/PassportNo, Name, Address, DateofBirth, Nationality, Gender, Password, DateofJoining, Income(Annual))" +
				" VALUES ( "+ c.getUserId() + ", '" +c.getNric() + "', '" + c.getName() + "','"+c.getAddress()+"','"+c.getBirthdate() +"','"+c.getNationality()+"', '"+c.getGender()+"','"+c.getPassword()+"','"+c.getJoinDate()+"','"+c.getAnnualIncome()+"');");*/
		
		try{
			conn.setAutoCommit(false);
			System.out.println(c.getName());
			//prepared statememtn used to insert values into Customer table
			PreparedStatement insertToCust;
			
			//prepared statement used to insert values into customer_bank table
			insertToCust= conn.prepareStatement("Insert into bank.customer values(?,?,?,?,?,?,?,?,?,?,?)");
			
			insertToCust.setString(1, c.getUserId());
			insertToCust.setString(2, c.getNric());
			insertToCust.setString(3, c.getName());
			insertToCust.setString(4, c.getAddress());
			insertToCust.setDate(5, new Date(c.getBirthdate().getTime()));
			insertToCust.setString(6,c.getNationality());
			insertToCust.setString(7, c.getGender());
			insertToCust.setString(8,c.getPassword());
			insertToCust.setDate(9,new Date(c.getJoinDate().getTime()));
			insertToCust.setBigDecimal(10, c.getAnnualIncome());
			insertToCust.setString(11, "active");
			
			int first=insertToCust.executeUpdate();
			conn.commit();
		
			insertToCust.close();
			closeConnection();
			return first;
		}
		catch(SQLException se){
			conn.rollback();
			throw new SQLException(se.getMessage());	
		}
	}
	
	
	
	
	@Override
	public int updateCustomer(Customer s,String searchID) throws SQLException,DuplicateKeyException {
		// TODO Auto-generated method stub
		openConnection();
		try{
			conn.setAutoCommit(false);
			String query="update customer set userId=?, NRIC_PassportNo=?, Name=?, Address=?, Password=? where userId=?";
			
			PreparedStatement updateToCust=conn.prepareStatement(query);
			updateToCust.setString(1, s.getUserId());
			updateToCust.setString(2, s.getNric());
			updateToCust.setString(3, s.getName());
			updateToCust.setString(4, s.getAddress());
			updateToCust.setString(5, s.getPassword());
			updateToCust.setString(6, searchID);
			
			int row=updateToCust.executeUpdate();
			conn.commit();
			updateToCust.close();
			closeConnection();
			
			return row;
		}
		catch(SQLException e){
			conn.rollback();
			e.printStackTrace();
			if(e.getErrorCode()==1062)
				throw new DuplicateKeyException("User ID Already Exit");
			throw new SQLException(e.getMessage());
		}
	}


	public void deleteCustomer(Customer s) throws SQLException{
		openConnection();
		
		try{
			PreparedStatement ps=conn.prepareStatement("UPDATE customer SET flag=? WHERE userId=?");
			ps.setString(1, "inactive");
			ps.setString(2, s.getUserId());
			ps.executeUpdate();
			
			ps.close();
			closeConnection();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
	
	@Override
	public Customer findCustomerById(String id){
		// TODO Auto-generated method stub
		openConnection();
		
		try{
			PreparedStatement ps=conn.prepareStatement("select * from customer where userId=? and flag=?");
			ps.setString(1, id);
			ps.setString(2, "active");
			
			ResultSet set=ps.executeQuery();
			
			Customer c=new Customer();
			SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy");
			
			while(set.next()){
				
				c.setUserId(set.getString("userId"));
				c.setAddress(set.getString("Address"));
				c.setName(set.getString("Name"));
				c.setNric(set.getString("NRIC_PassportNo"));
				c.setBirthdate(new java.util.Date(set.getDate("DateofBirth").getTime()));
				c.setGender(set.getString("Gender"));
				c.setPassword(set.getString("Password"));
				c.setNationality(set.getString("Nationality"));
				c.setJoinDate(new java.util.Date(set.getDate("DateofJoining").getTime()));
			}
			
			ps.close();
			closeConnection();
			
			return c;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}




	private void closeConnection() throws SQLException {
		// TODO Auto-generated method stub
		conn.close();

	}
	
	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bank", "root", "adminhtet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	

	/*@Override
	public ArrayList<Customer> createCustomer() throws SQLException  {
		// TODO Auto-generated method stub
		
		ArrayList<Customer> list= new ArrayList<Customer>();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");	
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	 Connection conn=null;
		Statement st;
		
		try
		{
		conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Password");
		st=conn.createStatement();
		ResultSet rs= st.executeQuery("INSERT INTO customer" +
                "VALUES (7, 'Zhiyaaaaa', 'Dang')");
		while(rs.next())
		{
		Customer c= new Customer(rs.........);
		list.add(c);
		}
		
		st.close();
		
		conn.close();
		return list;
		
		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		
			
		return null;
	}*/
	
	
	
	
	
}
