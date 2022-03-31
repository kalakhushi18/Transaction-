package com.capg.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Map;

import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capg.entity.Account;
import com.capg.entity.Transaction;
import com.capg.exceptions.TransferException;
import com.capg.functions.ImpFunctions;
import com.capg.repositroy.AccountRepository;
import com.capg.repositroy.TransactionRepository;
import com.capg.validator.AccountValidator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	private AccountValidator accountValidator;
	
	Account a , b ;
	
	double updatedBalanceA , updatedBalanceB ,amount ; 

	LocalDateTime instant;
	ImpFunctions function ;
	Transaction tran;

	@PostMapping("/detail")
	   public ResponseEntity<Transaction>  transferAmt(@RequestBody Transaction transaction  )throws TransferException{
		   
		   String transactionFrom = transaction.getTransactionFrom() ; 
		   String transactionTo = transaction.getTransactionTo() ; 
		   String transactionFromType = transaction.getTransactionFromType();
		   String transactionToType = transaction.getTransactionToType();
		   amount = transaction.getTransactionAmount() ; 
            
		   accountValidator = new AccountValidator();
	        
    if(accountValidator.validateType(transactionFromType , transactionToType) && accountValidator.validateAmount(amount) && accountValidator.validateAccountNumber(transactionFromType, transactionToType))  
    {
    	  a = accountRepository.findByaccountsNumber(transactionFrom);
          b = accountRepository.findByaccountsNumber(transactionTo);
          
        if(a!=null && b!=null) 
         {
        	   
           if(a.getAccountsType().equalsIgnoreCase(transactionFromType) && b.getAccountsType().equalsIgnoreCase(transactionToType))
            {		
	           if(amount <= a.getAccountsBalance())
				{
	        	   function = new ImpFunctions();
	        	   updateDetails(a,b);
	        	  
	        	    String time = function.getTime(instant);
	        	    
	                transaction.setTransactionDate( time);
	      		
	                 tran = transactionRepository.save(transaction);
					
				    return new ResponseEntity<Transaction>(tran, HttpStatus.CREATED);
				 }
	           
				 else 
				    throw new TransferException("Balance not enough ");
			  }
           
	        else 
	            throw new TransferException("The account Type doesn't match "); 
	       }
          else 
               throw new TransferException("The account number doesn't exists ");
    }
    else
       throw new TransferException("The input values are invalid");
 }
	
	
	
	public void updateDetails(Account first , Account second)
	{
		updatedBalanceA = a.getAccountsBalance() - amount;
		updatedBalanceB = b.getAccountsBalance() + amount;
		
		first.setAccountsBalance(updatedBalanceA);
		second.setAccountsBalance(updatedBalanceB);
		
		accountRepository.save(first);
	    accountRepository.save(second);
	}

}
