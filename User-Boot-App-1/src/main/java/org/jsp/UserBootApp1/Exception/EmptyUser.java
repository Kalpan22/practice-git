package org.jsp.UserBootApp1.Exception;

public class EmptyUser extends RuntimeException
{
	@Override
	public String getMessage() 
	{
		return "not a single User";
	}

}
