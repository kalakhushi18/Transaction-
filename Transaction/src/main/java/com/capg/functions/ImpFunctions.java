package com.capg.functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImpFunctions {

	public String getTime(LocalDateTime instant )
	{
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 instant = LocalDateTime.now();
		String currentTime =instant.format(myFormatObj);
		
		return currentTime ; 
	}
}
