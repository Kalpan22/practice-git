package org.jsp.UserBootApp1.Exception;

import org.jsp.UserBootApp1.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserBootExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handlerINFE(IdNotFoundException e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("User not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handlerICEH(InvalidCredentialExceptionHandler e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("invalid data enter or phone or email ");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handlerEU(EmptyUser e)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(e.getMessage());
		structure.setData("not a Single User is there ");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}