package com.team1a.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.team1a.dto.SavingAccount;

public class SavingAccountDAOImpl implements SavingAccountDAO {
	private static final String dbUrl = "jdbc:mysql://localhost:3306/bank";
	private static final String dbUserName = "root";
	private static final String dbPassword = "adminhtet";
	
	private Connection conn;
	
	private void openConnection()throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conn=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}
	
	private void closeConnection()throws SQLException{
		conn.close();
	}
	@Override
	public int createAccount(SavingAccount savingAcc,String holderID) throws SQLException {
		// TODO Auto-generated method stub
		try{
			openConnection();
			conn.setAutoCommit(false);
			System.out.println("AutoCommit false");;
			String insertQuery="INSERT INTO bankaccount (BankAccountNo,PIN,BBID,createDate,Balance,minBalance,accountType)  VALUES(?,?,?,?,?,?,?)";
			PreparedStatement insertToBank=conn.prepareStatement(insertQuery);
			
			insertToBank.setInt(1, savingAcc.getAccountNumber());
			insertToBank.setInt(2, savingAcc.getPinNo());
			insertToBank.setInt(3, 2);
			insertToBank.setDate(4, new Date(savingAcc.getCreateDate().getTime()));
			insertToBank.setBigDecimal(5, savingAcc.getBalance());
			insertToBank.setBigDecimal(6, savingAcc.getMinimumBalance());
			insertToBank.setString(7, "Saving");
			
			String updatQuery="INSERT INTO customer_bankacc VALUES (?,?)";
			PreparedStatement updateCustBank=conn.prepareStatement(updatQuery);

			updateCustBank.setInt(2, savingAcc.getAccountNumber());
			updateCustBank.setString(1, holderID);
			
			System.out.println("Bind To Parameter");
			
			int rows=insertToBank.executeUpdate();
			rows+=updateCustBank.executeUpdate();
			
			conn.commit();
			System.out.println("Commit");
			insertToBank.close();
			updateCustBank.close();
			closeConnection();
			
			return rows;
		}
		catch(SQLException e){
			System.out.println("Catch Exception");
			e.printStackTrace();
			conn.rollback();
			throw new SQLException(e.getMessage());
		}
	}
	
	public void updateAccount(SavingAccount sa)throws SQLException{
		try{
			openConnection();
			PreparedStatement ps=conn.prepareStatement("UPDATE bankaccount SET (PIN,minBalance) VALUES (?,?)");
			
			ps.setInt(1, sa.getPinNo());
			ps.setBigDecimal(2, sa.getMinimumBalance());
			
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
	public void deleteAccount(SavingAccount s) throws SQLException{
		try{
			openConnection();
			PreparedStatement ps=conn.prepareStatement("delete from bankaccount where BankAccountNo=?");
			ps.setInt(1, s.getAccountNumber());
			
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}
	public int getLastAccNo(){
		try{
			openConnection();
			
			//Statement st=conn.createStatement();
			Statement st=conn.createStatement();
			String query="select count(*) from bankaccount";
			ResultSet set=st.executeQuery(query);
			
			int last=0;
			while(set.next()){
				last=set.getInt(1);
			}
			
			return last;
		}
		catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
}
