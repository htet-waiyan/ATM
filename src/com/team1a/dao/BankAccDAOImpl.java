package com.team1a.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team1a.dto.BankAccount;
import com.team1a.dto.BankBranch;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.SavingAccount;
import com.team1a.dto.Transaction;
import com.team1a.util.NoAccountFound;
import com.team1a.util.TxnType;

public class BankAccDAOImpl implements BankAccDAO{

	private static final String dbUrl = "jdbc:mysql://localhost:3306/bank";
	private static final String dbUserName = "root";
	private static final String dbPassword = "adminhtet";
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet resultSet;
	
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
	public List<Transaction> getTransactionHistory(int accNo)
			throws SQLException {
		// TODO Auto-generated method stub
		try
		{
	          openConnection();
			
			
			PreparedStatement ps= conn.prepareStatement("select * from transaction where bankAccountNo=? ORDER BY transactionTime ASC;");
			
			
			ps.setInt(1, accNo);
			

			ResultSet set = ps.executeQuery();

			List<Transaction> TransList = new ArrayList<>();
			Transaction T= null;

			while (set.next()) {
				T=new Transaction();
				T.setTxnID(set.getInt(1));
				 T.setTxnType(set.getString("transactionType"));
				 T.setTxnAmount(set.getBigDecimal("transactionAmount"));
				 T.setToAccount(set.getInt("depositToAccount"));
				 T.setTxnTime(new java.util.Date(set.getTimestamp("transactionTime").getTime()));
				 BankAccount ba=new BankAccount();
				 ba.setAccountNumber(accNo);
				 T.setBankaccount(ba);
				 
				 TransList.add(T);
			
					}
			

			
			
			ps.close();
			closeConnection();
			
			return TransList;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;

		
	}

	public List<Transaction> getTransactionHistory(BankAccount ba)
			throws SQLException {
	try
	{
          openConnection();
		
		
		PreparedStatement ps= conn.prepareStatement("select * from transaction where bankAccountNo=? ORDER BY transactionTime ASC;");
		
		
		ps.setInt(1, ba.getAccountNumber());
		

		ResultSet set = ps.executeQuery();

		List<Transaction> TransList = new ArrayList<>();
		Transaction T= null;

		while (set.next()) {
			T=new Transaction();
			T.setTxnID(set.getInt(1));
			 T.setTxnType(set.getString("transactionType"));
			 T.setTxnAmount(set.getBigDecimal("transactionAmount"));
			 T.setToAccount(set.getInt("depositToAccount"));
			 T.setTxnTime(new java.util.Date(set.getTimestamp("transactionTime").getTime()));
			 T.setBankaccount(ba);
			 
			 TransList.add(T);
		
				}
		

		
		
		ps.close();
		closeConnection();
		
		return TransList;
	}
	catch(SQLException e){
		e.printStackTrace();
	}
	return null;

	
}	

	@Override
	public int createCheckingAccount(CheckingAccount checkingAcc,String holderID) throws SQLException {
		// TODO Auto-generated method stub
			openConnection();
		
		try{
			conn.setAutoCommit(false);
			String insertQuery="INSERT INTO bankaccount (BankAccountNo,PIN,BBID,createDate,Balance,overdraftAmount,accountType)" +
					" VALUES(?,?,?,?,?,?,?)";
			PreparedStatement insertToBank=conn.prepareStatement(insertQuery);
			
			insertToBank.setInt(1, checkingAcc.getAccountNumber());
			insertToBank.setInt(2, checkingAcc.getPinNo());
			insertToBank.setInt(3, 2);
			insertToBank.setDate(4, new Date(checkingAcc.getCreateDate().getTime()));
			insertToBank.setBigDecimal(5, checkingAcc.getBalance());
			insertToBank.setBigDecimal(6, checkingAcc.getOverdraftAmount());
			insertToBank.setString(7, "Checking");
			
			String updatQuery="INSERT INTO customer_bankacc VALUES (?,?)";
			PreparedStatement updateCustBank=conn.prepareStatement(updatQuery);
			
			updateCustBank.setInt(2, checkingAcc.getAccountNumber());
			updateCustBank.setString(1, holderID);
			
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
			e.printStackTrace();
			conn.rollback();
			throw new SQLException(e.getMessage());
		}
	}
	
	public void updateCheckingAccount(int pin,BigDecimal over, int acc)throws SQLException{
			openConnection();
			PreparedStatement ps=conn.prepareStatement("UPDATE bankaccount SET PIN=?, minBalance=? where BankAccountNo=?");
			
			ps.setInt(1, pin);
			ps.setBigDecimal(2, over);
			ps.setInt(3, acc);
			
			ps.executeUpdate();
			
			ps.close();
			closeConnection();
	}
	
	@Override
	public void updateSavingAccount(int pin, BigDecimal min,int acc)
			throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		PreparedStatement ps=conn.prepareStatement("UPDATE bankaccount SET PIN=?, minBalance=? where BankAccountNo=?");
		
		ps.setInt(1, pin);
		ps.setBigDecimal(2, min);
		ps.setInt(3,acc);
		
		ps.executeUpdate();
		
		ps.close();
		closeConnection();
	}

	public void deleteCheckingAccount(int acc) throws SQLException{
		try{
			openConnection();
			PreparedStatement ps=conn.prepareStatement("delete from bankaccount where BankAccountNo=?");
			ps.setInt(1, acc);
			
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
	public Map<Integer, BankAccount> getAllAccountsOf(String userId)
			throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		String query="select * from bankaccount where BankAccountNo In ( select cb.bankAccNo from customer_bankacc cb, bankaccount b where cb.bankAccNo=b.BankAccountNo and cb.userID=?)";
		
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setString(1, userId);
		
		ResultSet set=ps.executeQuery();
		
		Map<Integer, BankAccount> accLst=new HashMap<>();
		
		CheckingAccount ca=null;
		SavingAccount sa=null;
		
		while(set.next()){
			if(set.getString("accountType").equalsIgnoreCase("checking")){
				ca=new CheckingAccount();
				
				ca.setAccountNumber(set.getInt("BankAccountNo"));
				ca.setPinNo(set.getInt("PIN"));
				BankBranch b=new BankBranch();
				b.setBBID(set.getInt("BBID"));
				ca.setBranch(b);
				ca.setCreateDate(new java.util.Date(set.getDate("createDate").getTime()));
				ca.setBalance(set.getBigDecimal("Balance"));
				ca.setOverdraftAmount(set.getBigDecimal("overdraftAmount"));
				ca.setType(set.getString("accountType"));
				
				accLst.put(ca.getAccountNumber(), ca);
			}
			else if(set.getString("accountType").equalsIgnoreCase("saving")){
				sa=new SavingAccount();
				sa.setAccountNumber(set.getInt("BankAccountNo"));
				sa.setPinNo(set.getInt("PIN"));
				
				sa.setCreateDate(new java.util.Date(set.getDate("createDate").getTime()));
				sa.setBalance(set.getBigDecimal("Balance"));
				sa.setMinimumBalance(set.getBigDecimal("minBalance"));
				sa.setType(set.getString("accountType"));
				
				accLst.put(sa.getAccountNumber(), sa);
			}
		}
		
		return accLst;
	}
	
	@Override
	public void insertTransaction(Transaction txn)
			throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement insertToTxn=null;
		PreparedStatement updateToAcc=null;
		
		try{
			openConnection();
			conn.setAutoCommit(false);
			
			String txnQuery="insert into transaction (bankAccountNo,transactionType,transactionAmount,depositToAccount,transactionTime) values(?,?,?,?,?)";
			insertToTxn=conn.prepareStatement(txnQuery);
			insertToTxn.setInt(1, txn.getBankaccount().getAccountNumber());
			insertToTxn.setString(2, txn.getTxnType());
			insertToTxn.setBigDecimal(3, txn.getTxnAmount());
			insertToTxn.setInt(4, txn.getToAccount());
			insertToTxn.setTimestamp(5, new Timestamp(txn.getTxnTime().getTime()));
			
			String updateQuery="update bankaccount set Balance=? where BankAccountNo=? ";
			updateToAcc=conn.prepareStatement(updateQuery);
			
			updateToAcc.setBigDecimal(1, txn.getBankaccount().getBalance());
			updateToAcc.setInt(2, txn.getBankaccount().getAccountNumber());
			
			insertToTxn.executeUpdate();
			updateToAcc.executeUpdate();
			
			conn.commit();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			conn.rollback();
			throw new SQLException();
		}
		finally{
			if(updateToAcc!=null && insertToTxn!=null){
				updateToAcc.close();
				insertToTxn.close();
			}
		}
	}

	public int getLastAccNo(){
		try{
			openConnection();
			
			//Statement st=conn.createStatement();
			Statement st=conn.createStatement();
			String query="select BankAccountNo from bankaccount";
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
	
	public int createSavingAccount(SavingAccount savingAcc,String holderID) throws SQLException {
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
	
	public void updateSavingAccount(SavingAccount sa)throws SQLException{
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
	public void deleteSavingAccount(int acc) throws SQLException{
		try{
			openConnection();
			PreparedStatement ps=conn.prepareStatement("delete from bankaccount where BankAccountNo=?");
			ps.setInt(1, acc);
			
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
	public void insertTransaction(Transaction txn, BigDecimal toNewBalance) throws SQLException {
		// TODO Auto-generated method stub
		try{
			openConnection();
			
			
			String updateTxn="insert into transaction (bankAccountNo,transactionType,transactionAmount,depositToAccount,transactionTime) values(?,?,?,?,?)";
			String toAccSql="update bankaccount set Balance=? where BankAccountNo=?";
			String fromAccSql="update bankaccount set Balance=? where BankAccountNo=?";
			
			conn.setAutoCommit(false);
			PreparedStatement insertTxn=conn.prepareStatement(updateTxn);
			insertTxn.setInt(1, txn.getBankaccount().getAccountNumber());
			insertTxn.setString(2, txn.getTxnType());
			insertTxn.setBigDecimal(3, txn.getTxnAmount());
			insertTxn.setInt(4, txn.getToAccount());
			insertTxn.setDate(5, new Date(txn.getTxnTime().getTime()));
			
			PreparedStatement updateToAcc=conn.prepareStatement(toAccSql);
			updateToAcc.setBigDecimal(1, toNewBalance);
			updateToAcc.setInt(2,txn.getToAccount());
			
			PreparedStatement updateFromAcc=conn.prepareStatement(fromAccSql);
			updateFromAcc.setBigDecimal(1, txn.getBankaccount().getBalance());
			updateFromAcc.setInt(2, txn.getBankaccount().getAccountNumber());
			
			insertTxn.executeUpdate();
			updateToAcc.executeUpdate();
			updateFromAcc.executeUpdate();
			
			conn.commit();
			insertTxn.close();
			updateToAcc.close();
			updateFromAcc.close();
			closeConnection();
		}
		catch(SQLException e){
			conn.rollback();
			e.printStackTrace();
			
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public BigDecimal getCurrentBalanceOf(int accNo)
			throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		
		PreparedStatement ps=conn.prepareStatement("select Balance from bankaccount where BankAccountNo=?");
		ps.setInt(1, accNo);
		
		ResultSet set=ps.executeQuery();
		
		BigDecimal curBal=null;
		
		while(set.next()){
			curBal=set.getBigDecimal(1);
		}	
		
		return curBal;
	}
	
	
}
