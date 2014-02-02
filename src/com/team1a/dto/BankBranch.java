package com.team1a.dto;

public class BankBranch {
	int BBID;
	String location;
	String branchName;
	String description;
	
	public BankBranch(){}
	
	public BankBranch(int bBID, String location, String branchName,
			String description) {
		super();
		BBID = bBID;
		this.location = location;
		this.branchName = branchName;
		this.description = description;
	}
	public int getBBID() {
		return BBID;
	}
	public void setBBID(int bBID) {
		BBID = bBID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + BBID;
		result = prime * result
				+ ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankBranch other = (BankBranch) obj;
		if (BBID != other.BBID)
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
}
