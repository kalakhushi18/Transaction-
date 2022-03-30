package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	
 private int accountId ;
   
  @Id
  private BigInteger  accountsNumber;
  private String  accountsType ;
  private double  accountsBalance ;
  
  public Account()
  {
	  
  }
	public Account(int accountId, BigInteger accountsNumber, String accountsType, double accountsBalance) {
	
		this.accountId = accountId;
		this.accountsNumber = accountsNumber;
		this.accountsType = accountsType;
		this.accountsBalance = accountsBalance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public BigInteger getAccountsNumber() {
		return accountsNumber;
	}
	public void setAccountsNumber(BigInteger accountsNumber) {
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
	
	
  

}
