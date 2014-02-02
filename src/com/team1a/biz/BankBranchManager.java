package com.team1a.biz;

import com.team1a.dao.BankBranchDAO;
import com.team1a.dao.DAOFactory;
import com.team1a.dto.BankBranch;
import com.team1a.util.DuplicateKeyException;

import java.sql.*;
import java.util.List;

public class BankBranchManager {
	//private BankBranchDAO bbDAO;
	
		private BankBranchDAO bbDAO;

		

		public BankBranchManager() {
		bbDAO =DAOFactory.getBankBranchDAO();
		
		}

		public List<BankBranch> getAllBranch(){
			try{
				return bbDAO.retrieveAllBranch();
			}
			catch(SQLException e){
				e.printStackTrace();
				
				return null;
			}
		}
		
		public List<BankBranch> getBranchByName(String name){
			try{
				return bbDAO.getBranchByName(name);
			}
			catch(SQLException e){
				e.printStackTrace();
				
				return null;
			}
		}
		public int registerBankBranch(BankBranch bb)throws SQLException, DuplicateKeyException{
			int rowAffected=0;
			try{
				
				rowAffected=bbDAO.insertBankBranch(bb);
				
			}
			
			catch(SQLException e){
				throw new SQLException(e.getMessage());
			}
			
			return rowAffected;
		}
		
		public void updateBankBranch(BankBranch bb){
			try{
				bbDAO.updateBankBranch(bb);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		public void deleteBankBranch(int id){
			try {
				bbDAO.deleteBankBranch(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public BankBranch getBankBranchById(int id){
			return bbDAO.getBankBranchById(id);
		}
		
		public void deleteBranch(int id){
			try {
				bbDAO.deleteBankBranch(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
