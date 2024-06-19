package org.jsp.Practice.Exception;

import org.jsp.Practice.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserBootExceptionHandler 
{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("User not FOund");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("User not FOund");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	

}
