package com.team1a.dao;

import java.sql.SQLException;
import java.util.List;

import com.team1a.dto.BankBranch;
import com.team1a.util.DuplicateKeyException;

public interface BankBranchDAO {
	public int insertBankBranch(BankBranch bb)throws SQLException, DuplicateKeyException;
	
	public int updateBankBranch(BankBranch bb) throws SQLException;
	
	public void deleteBankBranch(int id)throws SQLException;
	
	public List<BankBranch> retrieveAllBranch()throws SQLException;
	
	public BankBranch getBankBranchById(int id);
	
	public List<BankBranch> getBranchByName(String name)throws SQLException;
}
