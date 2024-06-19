package org.jsp.UserBootApp1.Exception;

public class IdNotFoundException extends RuntimeException
{
	@Override
	public String getMessage() 
	{
		return "Invalid Id";
	}
}
