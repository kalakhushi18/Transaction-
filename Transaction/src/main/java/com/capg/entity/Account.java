package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	
 private int accountId ;
   
  @Id
  @Column(name = "Account_Number")
  private String  accountsNumber;
  
  @Column(name = "Account_Type")
  private String  accountsType ;
  
  @Column(name = "Account_Balance")
  private double  accountsBalance ;
  
  @Column(name = "Contact_Number")
  private int   contactNo ; 
  
	  
	
	public Account()
	  {
		  
	  }
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountsNumber() {
		return accountsNumber;
	}
	public void setAccountsNumber(String accountsNumber) {
		this.accountsNumber = accountsNumber;
	}
	public String getAccountsType() {
		return accountsType;
	}
	public void setAccountsType(String accountsType) {
		this.accountsType = accountsType;
	}
	public double getAccountsBalance() {
		return accountsBalance;
	}
	public void setAccountsBalance(double accountsBalance) {
		this.accountsBalance = accountsBalance;
	}
	
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	  public Account(int accountId, String accountsNumber, String accountsType, double accountsBalance,
		 int contactNo) {
			super();
			this.accountId = accountId;
			this.accountsNumber = accountsNumber;
			this.accountsType = accountsType;
			this.accountsBalance = accountsBalance;
		
			this.contactNo = contactNo;
		}
	
	
  

}
