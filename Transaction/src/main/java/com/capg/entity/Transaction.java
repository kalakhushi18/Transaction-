package com.capg.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Transaction {

	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int    transactionId ;
	  private double  transactionAmount ;
	  private Timestamp  transactionDate ;  
	   
	  private BigInteger transactionFrom ; 
      private BigInteger transactionTo ;
      
	      public Transaction()
	      {
	    	  
	      }
		public Transaction(int transactionId, double transactionAmount, Timestamp transactionDate, BigInteger transactionFrom,
				BigInteger transactionTo) {
			super();
			this.transactionId = transactionId;
			this.transactionAmount = transactionAmount;
			this.transactionDate = transactionDate;
			this.transactionFrom = transactionFrom;
			this.transactionTo = transactionTo;
		}
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public double getTransactionAmount() {
			return transactionAmount;
		}
		public void setTransactionAmount(double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}
		public Timestamp getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(Timestamp transactionDate) {
			this.transactionDate = transactionDate;
		}
		public BigInteger getTransactionFrom() {
			return transactionFrom;
		}
		public void setTransactionFrom(BigInteger transactionFrom) {
			this.transactionFrom = transactionFrom;
		}
		public BigInteger getTransactionTo() {
			return transactionTo;
		}
		public void setTransactionTo(BigInteger transactionTo) {
			this.transactionTo = transactionTo;
	} 

      
}
