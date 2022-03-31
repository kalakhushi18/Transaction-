package com.capg.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capg.entity.Account;
import com.capg.entity.Deposit;

import com.capg.entity.Withdraw;
import com.capg.exceptions.TransferException;
import com.capg.functions.ImpFunctions;
import com.capg.repositroy.AccountRepository;
import com.capg.repositroy.DepositRepository;
import com.capg.repositroy.EmployeeRepository;
import com.capg.repositroy.WithdrawRepository;
import com.capg.validator.AccountValidator;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  Withdraw withdraw ; 
	  Deposit deposit ; 
	
	
	 @Autowired
	 private EmployeeRepository employeeRepository ;
	
	  @Autowired
	   private AccountRepository accountRepository ; 
	  
	  @Autowired 
	  private WithdrawRepository withdrawRepository ; 
	  
	
	  @Autowired
	  private DepositRepository depositRepository ; 
	  
	  ImpFunctions function = new ImpFunctions();
	  private AccountValidator accountValidator = new AccountValidator();
	
	  Account a, b;
	  double updatedBalance ,depositAmt , withdrawAmt ,updatedBalanceB;
	  LocalDateTime instantOfDeposit,instantOfWithdraw  ;
	  
	  
	@PostMapping("/deposit")
	public ResponseEntity<Deposit>  depositAmt(@RequestBody Deposit d )throws TransferException
	{
		
		
		String accNo = d.getAccNo() ; 
	    String accType = d.getAccType() ; 
	    double depositAmt = d.getDepositAmt() ; 
	    if(accountValidator.validateTypeSingle(accType) && accountValidator.validateAmount(depositAmt) && accountValidator.validateAccountNumberSingle(accNo)) 
	    {
	    	Account a = accountRepository.findByaccountsNumber(accNo);
	    	if(a!=null) 
	    	{
		      if(a.getAccountsType().equalsIgnoreCase(accType))
		      {
		    	 double  updatedBalance = a.getAccountsBalance()+ depositAmt;
		    	  a.setAccountsBalance(updatedBalance);
		    	  accountRepository.save(a);
		    	 
		    	  String currentTime = function.getTime(instantOfDeposit);
		    	  d.setDate(currentTime);
		    	  deposit = depositRepository.save(d);
		    	  return new ResponseEntity<Deposit>(deposit, HttpStatus.CREATED);
		      }
		      else 
		      {
		    	  throw new TransferException("account doesn't exists with same type ");
		      }
	    	  
	         }
		    else
		    {
		    	throw new TransferException("account doesn't exists");
		    }
	    }else 
	    {
	    	throw new TransferException("input is invalid ");
	    }
	}

	
	@PostMapping("/withdraw")
	public ResponseEntity<Withdraw>  withdrawAmt(@RequestBody Withdraw w ) throws  TransferException
	{

		String accNo = w.getAccNo();
	    String accType = w.getAccType();
	    double withdrawAmt = w.getWithdrawAmt();
	    if(accountValidator.validateTypeSingle(accType) && accountValidator.validateAmount(withdrawAmt) && accountValidator.validateAccountNumberSingle(accNo)) 
	    {
	    	 b = accountRepository.findByaccountsNumber(accNo);
	      if(b!=null)
	      {
	    	  if(b.getAccountsType().equalsIgnoreCase(accType))
	    	  {
		    	  if(b.getAccountsBalance()>=withdrawAmt) 
		    	  {
		    		 
		    		double  updatedBalance = b.getAccountsBalance() - withdrawAmt;
		    		  b.setAccountsBalance(updatedBalance);
		        	  accountRepository.save(b);
				         String currentTime =function.getTime(instantOfWithdraw);
				         w.setDate(currentTime);
				    	 withdraw = withdrawRepository.save(w);
				    	return new ResponseEntity<Withdraw>(withdraw, HttpStatus.CREATED);
		    	  }
		    	  else 
		    	      throw new TransferException("Not enough Balance ");
		    	  
	         }
	    	  else 
	                throw new TransferException("account doesn't exists with same type ");
	    	  
	      }
	      else 
	          throw new TransferException("account doecn't exists ");
	      
	    }
	    else
	       throw new TransferException("input is invalid ");
	    
		
	}
	
	
}
