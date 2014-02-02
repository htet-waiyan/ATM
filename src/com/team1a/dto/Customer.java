package com.team1a.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {

	private String userId;
	private String nric;
	private String name;
	private String address;
	private Date birthdate;
	private String nationality;
	private String Gender;
	private String password;
	private Date joinDate;
	private BigDecimal annualIncome;
	
	private List<BankAccount> accountList=new ArrayList<>();

	public Customer(){}

	public Customer(String userId, String nric, String name, String address,
			Date birthdate, String nationality, String gender, String password,
			Date joinDate, BigDecimal annualIncome) {
		super();
		this.userId = userId;
		this.nric = nric;
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
		this.nationality = nationality;
		Gender = gender;
		this.password = password;
		this.joinDate = joinDate;
		this.annualIncome = annualIncome;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return Gender;
	}

	public void setAccountList(List<BankAccount> accountList) {
		this.accountList = accountList;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	
	public List<BankAccount> getAccountList() {
		return accountList;
	}
	
	public <U extends BankAccount> void addAccount(U u){
		accountList.add(u);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Gender == null) ? 0 : Gender.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((annualIncome == null) ? 0 : annualIncome.hashCode());
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result
				+ ((joinDate == null) ? 0 : joinDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((nric == null) ? 0 : nric.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Customer other = (Customer) obj;
		if (Gender == null) {
			if (other.Gender != null)
				return false;
		} else if (!Gender.equals(other.Gender))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (annualIncome == null) {
			if (other.annualIncome != null)
				return false;
		} else if (!annualIncome.equals(other.annualIncome))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (joinDate == null) {
			if (other.joinDate != null)
				return false;
		} else if (!joinDate.equals(other.joinDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (nric == null) {
			if (other.nric != null)
				return false;
		} else if (!nric.equals(other.nric))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", nric=" + nric + ", name="
				+ name + ", address=" + address + ", birthdate=" + birthdate
				+ ", nationality=" + nationality + ", Gender=" + Gender
				+ ", password=" + password + ", joinDate=" + joinDate
				+ ", annualIncome=" + annualIncome + "]";
	}

	
	
}
