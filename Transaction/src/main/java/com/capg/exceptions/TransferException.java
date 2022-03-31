package com.capg.exceptions;

import org.springframework.http.HttpStatus;

public class TransferException extends Exception{
	
	private HttpStatus s ; 
	public TransferException(String msg)
	{
	 
		super(msg);	
	}
	

}
