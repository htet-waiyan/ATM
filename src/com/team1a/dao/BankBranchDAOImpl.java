package com.team1a.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.team1a.dto.BankBranch;
import com.team1a.util.DuplicateKeyException;

public class BankBranchDAOImpl implements BankBranchDAO {

	Connection conn;
	PreparedStatement prep;

	public int insertBankBranch(BankBranch bb) throws SQLException,
			DuplicateKeyException {
		openConnection();

		conn.setAutoCommit(false);
		prep = conn
				.prepareStatement("Insert into bank.bankbranch values(?,?,?,?)");

		prep.setInt(1, bb.getBBID());
		prep.setString(2, bb.getLocation());
		prep.setString(3, bb.getBranchName());
		prep.setString(4, bb.getDescription());

		int result = prep.executeUpdate();
		conn.commit();
		prep.close();

		closeConnection();
		return result;
	}
	
	@Override
	public BankBranch getBankBranchById(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet set=null;
		try{
			openConnection();
			ps=conn.prepareStatement("select * from bankbranch where BBID=?");
			ps.setInt(1, id);
			set=ps.executeQuery();
			
			BankBranch bb=null;
			
			while(set.next()){
				bb=new BankBranch();
				bb.setBBID(set.getInt("BBID"));
				bb.setBranchName(set.getString("Name"));
				bb.setDescription(set.getString("Description"));
				bb.setLocation(set.getString("Location"));
			}
			
			ps.close();
			closeConnection();
			
			return bb;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateBankBranch(BankBranch bb) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();
		try {
			conn.setAutoCommit(false);
			String query = "update bankbranch set BBID=?, Location=?, Name=?, Description=?";

			PreparedStatement updateBankBranch = conn.prepareStatement(query);
			updateBankBranch.setInt(1, bb.getBBID());
			updateBankBranch.setString(2, bb.getLocation());
			updateBankBranch.setString(3, bb.getBranchName());
			updateBankBranch.setString(4, bb.getDescription());

			int row = updateBankBranch.executeUpdate();
			conn.commit();
			updateBankBranch.close();
			closeConnection();

			return row;
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
	}

	public void deleteBankBranch(int id) {
		openConnection();

		try {
			PreparedStatement ps = conn
					.prepareStatement("delete bankbranch WHERE BBID=?");

			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public List<BankBranch> retrieveAllBranch() throws SQLException {
		// TODO Auto-generated method stub
		openConnection();

		Statement st = conn.createStatement();
		ResultSet set = st.executeQuery("select * from bankbranch");

		List<BankBranch> bLst = new ArrayList<>();
		BankBranch bb = null;

		while (set.next()) {
			bb = new BankBranch();

			bb.setBBID(set.getInt("BBID"));
			bb.setBranchName(set.getString("Name"));
			bb.setDescription(set.getString("Description"));
			bb.setLocation(set.getString("Location"));

			bLst.add(bb);
		}

		st.close();
		closeConnection();
		return bLst;
	}

	@Override
	public List<BankBranch> getBranchByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		openConnection();

		PreparedStatement ps = conn
				.prepareStatement("select * from bankbranch where Name=?");
		ps.setString(1, name);

		ResultSet set = ps.executeQuery();

		List<BankBranch> bLst = new ArrayList<>();
		BankBranch bb = null;

		while (set.next()) {
			bb = new BankBranch();

			bb.setBBID(set.getInt("BBID"));
			bb.setBranchName(set.getString("Name"));
			bb.setDescription(set.getString("Description"));
			bb.setLocation(set.getString("Location"));

			bLst.add(bb);
		}
		
		ps.close();
		closeConnection();
		
		return bLst;
	}

}
