package com.capg.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Optional;

import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capg.entity.Account;
import com.capg.entity.Transaction;
import com.capg.repositroy.AccountRepository;
import com.capg.repositroy.TransactionRepository;
import java.time.Instant;  

@Controller
@RequestMapping("/transfer")
public class TransactionController {

	   @Autowired
	   private TransactionRepository transactionRepository;
	   
	   @Autowired
	   private AccountRepository accountRepository ; 
	   Timestamp instant ; 
	   Transaction  tran = null ; 
	   
	   
	   @PostMapping("/detail")
	   public ResponseEntity<Transaction>  createStudent(@RequestBody Transaction transaction ) {
		   
		   BigInteger transactionFrom = transaction.getTransactionFrom() ; 
		   BigInteger transactionTo = transaction.getTransactionTo() ; 
	        double amount = transaction.getTransactionAmount() ; 
	        instant= Timestamp.from(Instant.now());
	        BigInt
	        
	        if(isValidAccountNo(transactionFrom, transactionTo) && isValidAmt(amount)) 
	        {
	        	  Account a = accountRepository.findByaccountsNumber(transactionFrom);
		          Account b = accountRepository.findByaccountsNumber(transactionTo);
		          if(a!=null && b!=null) 
		          {
	        	  boolean existsFirst = accountRepository.existsById(a.getAccountId());
	        	  boolean existsSecond = accountRepository.existsById(b.getAccountId());
	        	  if(existsFirst && existsSecond)
	        	  {
			        		
			           if(amount <= a.getAccountsBalance())
						{
							double updatedBalanceA = a.getAccountsBalance() - amount;
							double updatedBalanceB = b.getAccountsBalance() + amount;
							a.setAccountsBalance(updatedBalanceA);
							b.setAccountsBalance(updatedBalanceB);
							   
					        transaction.setTransactionDate(instant);
							
							System.out.println("Amt tranferred ");
							
						    tran = transactionRepository.save(transaction);
							accountRepository.save(a);
						    accountRepository.save(b);
						    return new ResponseEntity<Transaction>(tran, HttpStatus.CREATED);
						}
						else 
						{
							System.out.println("Not enough Balance available");
							return new ResponseEntity<Transaction>(tran, HttpStatus.FORBIDDEN) ; 
						}
	        	  }
	        	  else 
	        	  {
	        		  System.out.println("Account number not available");
	        		  return new ResponseEntity<Transaction>(tran, HttpStatus.FORBIDDEN) ; 
	        	  }
	        }
		          else 
		          {
		        	  System.out.println("Account number not available");
	        		  return new ResponseEntity<Transaction>(tran, HttpStatus.FORBIDDEN) ; 
		          }
	        }
	        else
	        {
	        	System.out.println("Input is invalid ");
      		  return new ResponseEntity<Transaction>(tran, HttpStatus.FORBIDDEN) ;
	        }
	        
	     }
	 
	   @PostMapping("/deposit")
	   public ResponseEntity<Transaction>  createStudent(@RequestBody Account account ) 
	   {
		   BigInteger accNumber = account.getAccountsNumber();
		   String accType = account.getAccountsType();
//		   double amountDeposit = 
		   return new ResponseEntity<Transaction>(tran, HttpStatus.FORBIDDEN) ; 
	   }
	   
	   public Boolean isValidAccountNo(BigInteger accNoFirst , BigInteger accNoSecond)
	   {
		   
		   return true ;
		   
	   }
	   
	   public Boolean isValidAmt(double amount)
	   {
		   if(!Double.isNaN(amount))
		   {
			   if(amount >0)
				 return true ; 
			   else 
				  return false ; 
		   }
		   else 
			   return false ; 
		}
}
